package parser;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import calendar.XMLCal;


import event.Event;
import event.NFLEvent;

public class NFLParser extends Parser {

	private final String eventName = "row";

	public NFLParser(Element root) {
		super(root);
	}

	@Override
	public XMLCal parse() throws ParseException {
		List<Element> elements = getRoot().elements(eventName);
		List<Event> events = new ArrayList<Event>();

		for (Element element : elements)
			events.add(new NFLEvent(element));

		return new XMLCal(events);
	}

	public static class Factory extends Parser.Factory {

		private final String nflRoot = "document";

		public boolean isType(Element root) {

			return root.getName().equals(nflRoot);
		}

		public Parser buildCalendar(Element root) {
			return new NFLParser(root);
		}
	}
}
