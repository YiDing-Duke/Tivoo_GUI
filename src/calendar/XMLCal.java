package calendar;

import java.util.List;

import event.Event;

public class XMLCal {

	private List<Event> events;

	public XMLCal(List<Event> events) {
		this.events = events;
	}

	// returns a list of Events
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
