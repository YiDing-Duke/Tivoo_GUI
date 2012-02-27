package event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.dom4j.Element;

public class TVEvent extends Event {
	private ArrayList<String> actors;

	public ArrayList<String> getActors() {
		return actors;
	}

	public TVEvent(Element event) throws ParseException {
		setTitle(event.elementText("title"));
		setDescription(event.elementText("desc"));
		setStart(parseTime(event, "start"));
		setEnd(parseTime(event, "stop"));
		setActors(event);
	}

	private void setActors(Element event) {
		ArrayList<String> actors = new ArrayList<String>();
		if (event.element("credits") != null) {
			if (event.element("credits").elements("actor") != null) {
				List<Element> elements = event.element("credits").elements("actor");
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
		result.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(start));

		return result;
	}
}