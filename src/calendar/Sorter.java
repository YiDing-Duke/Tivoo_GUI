package calendar;

import java.util.Collections;

import event.Event;

public class Sorter {
	
	private XMLCal calendar;
	
	public Sorter(XMLCal calendar)
	{
		this.calendar = calendar;
	}
	
	public XMLCal getCalendar()
	{
		return calendar;
	}
	
	public void sortByTitle(boolean reverse) {

			Collections.sort(calendar.getEvents(), Event.byTitle);
			if (reverse)
				Collections.reverse(calendar.getEvents());
	}

	public  void sortStart(boolean reverse) {

		Collections.sort(calendar.getEvents(), Event.byStart);
		if (reverse)
			Collections.reverse(calendar.getEvents());
}
	public void sortByEnd(boolean reverse) {

		Collections.sort(calendar.getEvents(), Event.byEnd);
		if (reverse)
			Collections.reverse(calendar.getEvents());
}
		

}
