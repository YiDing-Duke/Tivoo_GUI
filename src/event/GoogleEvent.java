package event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Element;

public class GoogleEvent extends Event {

	public GoogleEvent(Element event) throws ParseException {
		// sets title, time, description etc. of event

		setTitle(event.elementText("title"));
		parseTime(event);
		setDescription(event.elementText("summary"));
		setLink(event.element("link").attributeValue("href"));
		setAuthor(event.element("author").elementText("name"));
	}

	private void parseTime(Element event) throws ParseException {
		Pattern time = Pattern.compile("When:\\s(.*?)\\sto\\s(.*)");
		Matcher matcher = time.matcher(event.element("content").getText());
		if (matcher.find()) {
			Calendar start = new GregorianCalendar();
			Calendar end = new GregorianCalendar();
			Calendar temp = new GregorianCalendar();

			DateFormat group1 = new SimpleDateFormat("EEE MMM dd, yyyy hh:mmaa");
			DateFormat group2 = new SimpleDateFormat("hh:mmaa");

			String startTimeString = matcher.group(1);
			startTimeString = fixTimeFormat(startTimeString);
			start.setTime((group1.parse(startTimeString)));

			end.setTime(start.getTime());
			String endTimeString = matcher.group(2);
			endTimeString = endTimeString.substring(0,
					endTimeString.length() - 1);

			endTimeString = fixTimeFormat(endTimeString);
			temp.setTime((group2.parse(endTimeString)));

			end.set(Calendar.HOUR, temp.get(Calendar.HOUR));
			end.set(Calendar.MINUTE, temp.get(Calendar.MINUTE));

			setStart(start);
			setEnd(end);
		} else {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			setStart(calendar);
			setEnd(calendar);
		}
	}

	private String fixTimeFormat(String startString) {
		StringBuffer dateStringBuffer = new StringBuffer(startString);
		int dateStringLength = dateStringBuffer.length();

		if (dateStringLength < 5
				|| dateStringBuffer.charAt(dateStringLength - 5) != ':') {
			dateStringBuffer.insert(dateStringLength - 2, ":00");
			startString = dateStringBuffer.toString();
		}
		return startString;
	}
}
