package event;

import java.util.Calendar;
import java.util.Comparator;

public abstract class Event {

	private String title;
	private Calendar start;
	private Calendar end;
	private String description;
	private String link;
	private String author;

	public static final Comparator<Event> byTitle = new Comparator<Event>() {
		public int compare(Event e1, Event e2) {
			return e1.getTitle().compareTo(e2.getTitle());
		}
	};

	public static final Comparator<Event> byStart = new Comparator<Event>() {
		public int compare(Event e1, Event e2) {
			return e1.getStart().compareTo(e2.getStart());
		}
	};

	public static final Comparator<Event> byEnd = new Comparator<Event>() {
		public int compare(Event e1, Event e2) {
			return e1.getEnd().compareTo(e2.getEnd());
		}
	};

	public Integer getUniqueDay() {
		return getStart().get(Calendar.DAY_OF_YEAR)
				+ getStart().get(Calendar.YEAR) * 365;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public Calendar getStart() {
		return start;
	}

	public void setEnd(Calendar end) {
		this.end = end;
	}

	public Calendar getEnd() {
		return end;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

}
