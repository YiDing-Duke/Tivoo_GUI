package calendar;

import java.util.ArrayList;

import event.Event;
import event.TVEvent;

public class NativeFilter extends Filter{

	public NativeFilter(XMLCal calendar) {
		super(calendar);
	}
	
	//filters by actor
	public void filterActor(String[] filters) {
		ArrayList<Event> filtration = new ArrayList<Event>();
		
		for (Event event : getCalendar().getEvents())
			if (event instanceof TVEvent)
				for (String filter : filters) {
					TVEvent tvevent = (TVEvent) event;
					if (tvevent.getActors().contains(filter))
						filtration.add(event);
				}
			else
				filtration.add(event);
		
		getCalendar().setEvents(filtration);
	}

}
