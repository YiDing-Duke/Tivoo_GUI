package event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.dom4j.Element;

public class DukeBasketballEvent extends Event {
    private static final String dateFormat = "MM/DD/yyyyhh:mm:ss aa";
    private static final String title = "Subject";
    private static final String descriptionString = "Description";
    private static final String startDateString = "StartDate";
    private static final String startTimeString = "StartTime";
    private static final String endDateString = "EndDate";
    private static final String endTimeString = "EndTime";
    private static final String linkString = "Location";
    private static final String authorString = "Location";
	public DukeBasketballEvent(Element event) throws ParseException {
		// sets title, time, description etc. of event

		setTitle(event.elementText(title));
		setDescription(event.elementText(descriptionString));
		setStart(parseTime(event, startDateString, startTimeString));
		setEnd(parseTime(event, endDateString, endTimeString));
		setLink(event.elementText(linkString));
		setAuthor(event.elementText(authorString));
	}

	private Calendar parseTime(Element event, String dateCategory,
			String timeCategory) throws ParseException {
		String startDate = event.elementText(dateCategory);
		String startTime = event.elementText(timeCategory);
		String combinedDateTime = startDate + startTime;
		Calendar result = new GregorianCalendar();
		result.setTime(new SimpleDateFormat(dateFormat)
				.parse(combinedDateTime));
		return result;
	}
}
