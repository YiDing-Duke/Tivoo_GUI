package view;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.H2;
import com.hp.gagawa.java.elements.H3;
import com.hp.gagawa.java.elements.P;

import event.Event;

public class EventHTMLGenerator {
    private static final String dayOfWeekFormat = "%1$tB %1$te, %1$tY";
	public Event event;

	public EventHTMLGenerator(Event event) {
		this.event = event;
	}
	
	public String getPageTitle() throws NoSuchAlgorithmException {

		String uniquetitle = event.getTitle()
				+ event.getStart().getTime().toString();

		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(uniquetitle.getBytes());

		BigInteger hash = new BigInteger(1, md5.digest());
		return hash.toString(16);

	}

	private A summaryTitle() throws NoSuchAlgorithmException {
		A titlelink = new A();
		H3 title = new H3();

		titlelink.appendText(event.getTitle())
				.setHref("details/" + getPageTitle() + ".html")
				.setTarget("_blank");
		title.appendChild(titlelink);

		return titlelink;
	}

	private H1 title() {
		return new H1().appendText(event.getTitle());
	}
	
	private H2 dayOfWeek() {
		return new H2().appendText(String.format(dayOfWeekFormat,
				event.getStart()));
	}

	private P startTime() {
		return new P().appendText("Start Time: "
				+ event.getStart().getTime().toString());
	}

	private P endTime() {
		return new P().appendText("End Time: "
				+ event.getEnd().getTime().toString());
	}

	private H3 author() {
		return new H3().appendText(event.getAuthor());
	}

	private P description() {
		return new P().appendText("Description: " + event.getDescription());
	}

	public Div getSummaryEvent(Integer oldday)
			throws NoSuchAlgorithmException {
		Div element = new Div();

		Integer day = event.getUniqueDay();
		if (!oldday.equals(day))
			element.appendChild(dayOfWeek());
		oldday = day;

		element.appendChild(summaryTitle()).appendChild(startTime())
				.appendChild(endTime());

		return element;
	}

	public Div getEvent() {
		Div document = new Div();

		document.appendChild(title()).appendChild(author())
				.appendChild(startTime()).appendChild(endTime())
				.appendChild(description());

		return document;
	}
}
