package calendar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import event.Event;

public class TimeFilter extends Filter {

	public TimeFilter(XMLCal calendar) {
		super(calendar);
	}

	// filter by time frame
	public void filterTime(Calendar before, Calendar after)
			throws ParseException {
		ArrayList<Event> filtration = new ArrayList<Event>();

		for (Event event : getCalendar().getEvents()) {
			if (event.getStart().before(after)
					&& event.getStart().after(before))
				filtration.add(event);
		}

		getCalendar().setEvents(filtration);
	}

}
