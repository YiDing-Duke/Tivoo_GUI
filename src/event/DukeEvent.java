package event;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.dom4j.Element;

public class DukeEvent extends Event {
    private static final String dateFormat = "yyyyMMdd'T'HHmmss'Z'";
    private static final String title = "summary";
    private static final String description = "description";
    private static final String startTime = "start";
    private static final String endTime = "end";
    private static final String link = "link";
    private static final String utcdate = "utcdate";
	public DukeEvent(Element event) throws ParseException {
		// sets title, time, description etc. of event
		setTitle(event.elementText(title));
		setStart(parseTime(event, startTime));
		setEnd(parseTime(event, endTime));
		setDescription(event.elementText(description));
		setLink(event.elementText(link));
		setAuthor(event.element("contact").elementText("name"));
	}

	// parses the times of events
	private Calendar parseTime(Element event, String element) throws ParseException {
		String start = event.element(element).element(utcdate).getText();
		Calendar result = new GregorianCalendar();
		result.setTime(new SimpleDateFormat(dateFormat).parse(start));
		return result;
	}
}
