package event;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.dom4j.Element;

public class DukeEvent extends Event {

	public DukeEvent(Element event) throws ParseException {
		// sets title, time, description etc. of event
		setTitle(event.elementText("summary"));
		setStart(parseTime(event, "start"));
		setEnd(parseTime(event, "end"));
		setDescription(event.elementText("description"));
		setLink(event.elementText("link"));
		setAuthor(event.element("contact").elementText("name"));
	}

	// parses the times of events
	private Calendar parseTime(Element event, String element) throws ParseException {
		String start = event.element(element).element("utcdate").getText();
		Calendar result = new GregorianCalendar();
		result.setTime(new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'").parse(start));
		return result;
	}
}
