package event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.dom4j.Element;

public class TVEvent extends Event {
    private static final String title = "title";
    private static final String descriptionString = "desc";
    private static final String dateFormat = "yyyyMMddHHmmss";
    private static final String startTimeString = "start"; 
    private static final String endTimeString = "end";
	private ArrayList<String> actors;

	public ArrayList<String> getActors() {
		return actors;
	}

	public TVEvent(Element event) throws ParseException {
		// sets title, time, description etc. of event

		setTitle(event.elementText(title));
		setDescription(event.elementText(descriptionString));
		setStart(parseTime(event, startTimeString));
		setEnd(parseTime(event, endTimeString));
		setActors(event);
	}

	private void setActors(Element event) {
		ArrayList<String> actors = new ArrayList<String>();
		if (event.element("credits") != null) {
			if (event.element("credits").elements("actor") != null) {
				List<Element> elements = event.element("credits").elements(
						"actor");
				for (Element element : elements) {
					actors.add(element.getText());
				}
			}
		}
		this.actors = actors;
	}

	private Calendar parseTime(Element event, String element)
			throws ParseException {
		Calendar result = new GregorianCalendar();
		String start = event.attributeValue(element);
		result.setTime(new SimpleDateFormat(dateFormat).parse(start));

		return result;
	}
}
