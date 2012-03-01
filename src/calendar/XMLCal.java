package calendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import event.Event;

public class XMLCal {

	private List<Event> events;
	
	public XMLCal(List<Event> events) {
		this.events = events;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
