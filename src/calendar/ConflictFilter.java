package calendar;

import java.util.ArrayList;

import event.Event;

public class ConflictFilter extends Filter{

	public ConflictFilter(XMLCal calendar) {
		super(calendar);
	}
	
	//filters by conflict
	public void filterConflicts() {
		ArrayList<Event> filtration = new ArrayList<Event>();

		for (Event event1 : getCalendar().getEvents()) {
			for (Event event2 : getCalendar().getEvents()) {
				if (!event1.getTitle().equals(event2.getTitle())
						&& !filtration.contains(event1)) {
					if (event1.getStart().before(event2.getEnd())
							|| event1.getStart().after(event2.getStart())) {
						filtration.add(event1);
					}
				}
			}
		}

		getCalendar().setEvents(filtration);
	}
}
