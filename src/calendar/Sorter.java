package calendar;

import java.util.Collections;

import event.Event;

public class Sorter {

	private XMLCal calendar;

	public Sorter(XMLCal calendar) {
		this.calendar = calendar;
	}

	public XMLCal getCalendar() {
		return calendar;
	}

	// reverses the sort
	public void reverse() {
		Collections.reverse(calendar.getEvents());
	}

	// sorts by title
	public void sortByTitle() {
		Collections.sort(calendar.getEvents(), Event.byTitle);
	}

	// sort by start of event
	public void sortStart() {
		Collections.sort(calendar.getEvents(), Event.byStart);
	}

	// sort by end of event
	public void sortByEnd() {
		Collections.sort(calendar.getEvents(), Event.byEnd);
	}

}
