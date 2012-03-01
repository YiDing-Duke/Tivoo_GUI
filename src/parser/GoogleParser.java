package parser;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import calendar.XMLCal;


import event.Event;
import event.GoogleEvent;

public class GoogleParser extends Parser {

	private final String eventName = "entry";

	public GoogleParser(Element root) {
		super(root);
	}

	public XMLCal parse() throws ParseException {
		List<Element> elements = getRoot().elements(eventName);
		List<Event> events = new ArrayList<Event>();

		for (Element element : elements)
			events.add(new GoogleEvent(element));


		return new XMLCal(events);
	}

	public static class Factory extends Parser.Factory {

		private final String googRoot = "feed";

	    // checks to see what type of calendar
		public boolean isType(Element root) {

			return root.getName().equals(googRoot);
		}

		public Parser buildCalendar(Element root) {
			return new GoogleParser(root);
		}

	}

}
