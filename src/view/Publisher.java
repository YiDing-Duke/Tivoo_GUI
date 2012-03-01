package view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import calendar.XMLCal;

import com.hp.gagawa.java.elements.Div;

import event.Event;

public class Publisher {
	private XMLCal events;

	public Publisher(XMLCal calendar) {
		this.events = calendar;
	}

	public void publish() throws IOException, NoSuchAlgorithmException {
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
				"resources/summary.html"));
		out.print(document.write());
		out.close();
	}

	public void publishEvent(Event event) throws NoSuchAlgorithmException,
			IOException {
		EventHTMLGenerator html = new EventHTMLGenerator(event);
		Div document = html.getEvent();

		PrintWriter out = new PrintWriter(new FileWriter("resources/details/"
				+ html.getPageTitle() + ".html"));
		out.print(document.write());
		out.close();
	}

}