package event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.dom4j.Element;

public class DukeBasketballEvent extends Event {
	public DukeBasketballEvent(Element event) throws ParseException {
		// sets title, time, description etc. of event

		setTitle(event.elementText("Subject"));
		setDescription(event.elementText("Description"));
		setStart(parseTime(event, "StartDate", "StartTime"));
		setEnd(parseTime(event, "EndDate", "EndTime"));
		setLink(event.elementText("Location"));
		setAuthor(event.elementText("Location"));
	}

	private Calendar parseTime(Element event, String dateCategory,
			String timeCategory) throws ParseException {
		String startDate = event.elementText(dateCategory);
		String startTime = event.elementText(timeCategory);
		String combinedDateTime = startDate + startTime;
		Calendar result = new GregorianCalendar();
		result.setTime(new SimpleDateFormat("MM/DD/yyyyhh:mm:ss aa")
				.parse(combinedDateTime));
		return result;
	}
}
