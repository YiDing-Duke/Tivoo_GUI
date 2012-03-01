package parser;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import calendar.XMLCal;


import event.DukeBasketballEvent;
import event.Event;

public class BasketballParser extends Parser{
    private final String eventName = "Calendar";
    public BasketballParser(Element root) {
        super(root);
    }

    @Override
    public XMLCal parse() throws ParseException {
        List<Element> elements = getRoot().elements(eventName);
        List<Event> events = new ArrayList<Event>();

        for (Element element : elements)
            events.add(new DukeBasketballEvent(element));

        return new XMLCal(events);
    }
    
    public static class Factory extends Parser.Factory {

        private final String dukebballRoot = "dataroot";

       // checks to see what type of calendar
        public boolean isType(Element root) {

            return root.getName().equals(dukebballRoot);
        }

        public Parser buildCalendar(Element root) {
            return new BasketballParser(root);
        }
    }

}
