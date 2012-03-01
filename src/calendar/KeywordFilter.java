package calendar;

import java.util.ArrayList;
import event.Event;

public class KeywordFilter extends Filter{

	public KeywordFilter(XMLCal calendar) {
		super(calendar);
	}
	
	interface FilterCommander {
		public boolean call(String filter, Event event);
	}
	
	
	private void filter(String[] filters, FilterCommander contains) {
		ArrayList<Event> filtration = new ArrayList<Event>();
		for (Event event : getCalendar().getEvents())
			for (String filter : filters)
				if (contains.call(filter, event))
					filtration.add(event);
		getCalendar().setEvents(filtration);
	}
	
	//filters by title
	public void filterTitle(String[] filters) {
		filter(filters, new FilterCommander() {
			public boolean call(String filter, Event event) {
				return event.getTitle().contains(filter);
			}
		});
	}
	
	//filters by description
	public void filterDescription(String[] filters) {
		filter(filters, new FilterCommander() {
			public boolean call(String filter, Event event) {
				return event.getDescription().contains(filter);
			}
		});
	}
	
	//filters by author
	public void filterAuthor(String[] filters) {
		filter(filters, new FilterCommander() {
			public boolean call(String filter, Event event) {
				return event.getAuthor().contains(filter);
			}
		});
	}

}
