package view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Calendar;

import calendar.XMLCal;

import com.hp.gagawa.java.elements.Div;

import event.Event;

public class Publisher {
    private static final String summaryFileLocation = "resources/summary.html";
    private static final String detailsFileLocation = "resources/details/";
	private XMLCal events;

	public Publisher(XMLCal calendar) {
		this.events = calendar;
	}

	public void publish() throws IOException, NoSuchAlgorithmException {
		publishSummary();
		for (Event event : events.getEvents())
			publishEvent(event);
	}

	public void publishOrdered() throws IOException, NoSuchAlgorithmException {
		events.sort(XMLCal.SortType.start, false);
		publishSummary();
		for (Event event : events.getEvents())
			publishEvent(event);
	}

	public void publishSummary() throws IOException, NoSuchAlgorithmException {
		Div document = new Div();

		Integer oldday = 0;

		for (Event event : events.getEvents()) {
			Div element = new EventHTMLGenerator(event).getSummaryEvent(oldday);
			document.appendChild(element);
			oldday = event.getUniqueDay();
		}

		PrintWriter out = new PrintWriter(new FileWriter(
				summaryFileLocation));
		out.print(document.write());
		out.close();
	}

	public void publishEvent(Event event) throws NoSuchAlgorithmException,
			IOException {
		EventHTMLGenerator html = new EventHTMLGenerator(event);
		Div document = html.getEvent();

		PrintWriter out = new PrintWriter(new FileWriter(detailsFileLocation
				+ html.getPageTitle() + ".html"));
		out.print(document.write());
		out.close();
	}

	public void publishCalendar(XMLCal.CalendarType type, Calendar start)
			throws ParseException, NoSuchAlgorithmException, IOException {
		events.filter(type, start);
		publishOrdered();
	}

	public void publishConflicts()
			throws ParseException, NoSuchAlgorithmException, IOException {
		events.getConflicts();
		publishOrdered();
	}

}