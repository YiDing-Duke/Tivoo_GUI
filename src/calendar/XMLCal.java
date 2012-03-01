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
	
	public static enum SortType {
		title, start, end,
	};


	public List<Event> reverse(Stack<Event> filtration, boolean reverse) {
		ArrayList<Event> filtEvents = new ArrayList<Event>();

		if (reverse)
			while (!filtration.isEmpty())
				filtEvents.add(filtration.pop());
		else
			for (int i = 0; i < filtration.size(); i++)
				filtEvents.add(filtration.get(i));

		return filtEvents;
	}

	public void sort(SortType type, boolean reverse) {
		switch (type) {
		case title:
			Collections.sort(events, Event.byTitle);
			break;

		case start:
			Collections.sort(events, Event.byStart);
			break;

		case end:
			Collections.sort(events, Event.byEnd);
			break;
		}

		if (reverse)
			Collections.reverse(events);
	}
}
