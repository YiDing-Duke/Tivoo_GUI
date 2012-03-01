package parser;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import calendar.XMLCal;

import event.DukeEvent;
import event.Event;

public class DukeParser extends Parser {

	private final String eventName = "event";

	public DukeParser(Element root) {
		super(root);
	}
	
	public XMLCal parse() throws ParseException {
		List<Element> elements = getRoot().elements(eventName);
		List<Event> events = new ArrayList<Event>();

		for (Element element : elements)
			events.add(new DukeEvent(element));

		return new XMLCal(events);
	}

	public static class Factory extends Parser.Factory {

		private final String dukeRoot = "events";

	    // checks to see what type of calendar
		public boolean isType(Element root) {
			return root.getName().equals(dukeRoot);
		}

		public Parser buildCalendar(Element root) {
			return new DukeParser(root);
		}

	}
}
