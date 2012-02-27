package calendar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Stack;

import event.Event;
import event.TVEvent;

public class XMLCal {

	private List<Event> events;

	public List<Event> getEvents() {
		return events;
	}

	public static enum FilterType {
		title, description, author, actor
	};

	public static enum SortType {
		title, start, end, calendar
	};

	public enum CalendarType {
		day, week, month;
	}

	public XMLCal(List<Event> events) {
		this.events = events;
	}

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

	public void filter(Calendar before, Calendar after) throws ParseException {
		ArrayList<Event> filtration = new ArrayList<Event>();

		for (Event event : events) {
			if (event.getStart().before(after)
					&& event.getStart().after(before))
				filtration.add(event);
		}

		events = filtration;
	}

	public void filter(CalendarType type, Calendar start) throws ParseException {
		Calendar end = new GregorianCalendar();
		if (type.equals(CalendarType.day)) {
			end.set(Calendar.YEAR, start.get(Calendar.YEAR));
			end.set(Calendar.DAY_OF_YEAR, start.get(Calendar.DAY_OF_YEAR));
		} else if (type.equals(CalendarType.week)) {
			end.set(Calendar.YEAR, start.get(Calendar.YEAR));
			end.set(Calendar.WEEK_OF_YEAR, start.get(Calendar.WEEK_OF_YEAR) + 1);
		} else if (type.equals(CalendarType.month)) {
			end.set(Calendar.YEAR, start.get(Calendar.YEAR));
			end.set(Calendar.MONTH, start.get(Calendar.MONTH));
		}

		filter(start, end);
	}

	public void getConflicts() {
		ArrayList<Event> filtration = new ArrayList<Event>();

		for (Event event1 : events) {
			for (Event event2 : events) {
				if (!event1.getTitle().equals(event2.getTitle())
						&& !filtration.contains(event1)) {
					if (event1.getStart().before(event2.getEnd())
							|| event1.getStart().after(event2.getStart())) {
						filtration.add(event1);
					}
				}
			}
		}

		events = filtration;
	}

	public void filter(FilterType type, String[] filters) {
		ArrayList<Event> filtration = new ArrayList<Event>();

		switch (type) {
		case title:
			for (Event event : events)
				for (String filter : filters)
					if (event.getTitle().contains(filter))
						filtration.add(event);
			break;
		case description:
			for (Event event : events)
				for (String filter : filters)
					if (event.getDescription().contains(filter))
						filtration.add(event);
			break;
		case author:
			for (Event event : events)
				for (String filter : filters)
					if (event.getAuthor().contains(filter))
						filtration.add(event);
			break;
		case actor:
			for (Event event : events)
				if (event instanceof TVEvent)
					for (String filter : filters) {
						TVEvent tvevent = (TVEvent) event;
						if (tvevent.getActors().contains(filter))
							filtration.add(event);
					}
				else
					filtration.add(event);
			break;
		}

		events = filtration;
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
