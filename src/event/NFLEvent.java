package event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.dom4j.Element;

public class NFLEvent extends Event {
	public NFLEvent(Element event) throws ParseException {
		// sets title, time, description etc. of event

		setTitle(event.elementText("Col1"));
		setDescription(event.elementText("Col15"));
		setStart(parseTime(event, "Col8"));
		setEnd(parseTime(event, "Col9"));
		setLink(event.elementText("Col2"));
		setAuthor(event.elementText("Col3"));
	}

	private Calendar parseTime(Element event, String element)
			throws ParseException {
		String start = event.elementText(element);
		Calendar result = new GregorianCalendar();
		result.setTime(new SimpleDateFormat("yyyy'-'MM'-'dd HH:mm:ss")
				.parse(start));
		return result;

	}
}
