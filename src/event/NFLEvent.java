package event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.dom4j.Element;

public class NFLEvent extends Event {
    private static final String title = "Col1";
    private static final String description = "Col15";
    private static final String dateFormat = "yyyy'-'MM'-'dd HH:mm:ss";
    private static final String startTime = "Col8"; 
    private static final String endTime = "Col9";
    private static final String link = "Col2";
    private static final String author = "Col3";
	public NFLEvent(Element event) throws ParseException {
		// sets title, time, description etc. of event

		setTitle(event.elementText(title));
		setDescription(event.elementText(description));
		setStart(parseTime(event, startTime));
		setEnd(parseTime(event, endTime));
		setLink(event.elementText(link));
		setAuthor(event.elementText(author));
	}

	private Calendar parseTime(Element event, String element)
			throws ParseException {
		String start = event.elementText(element);
		Calendar result = new GregorianCalendar();
		result.setTime(new SimpleDateFormat(dateFormat)
				.parse(start));
		return result;

	}
}
