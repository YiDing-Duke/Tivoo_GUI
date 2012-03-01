package calendar;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarFilter extends TimeFilter {

	public CalendarFilter(XMLCal calendar) {
		super(calendar);
	}
	
	//filters by day
	public void filterDay(Calendar start) throws ParseException {
		Calendar end = new GregorianCalendar();
		Calendar newstart = new GregorianCalendar();
		
		newstart.set(Calendar.YEAR, start.get(Calendar.YEAR));
		newstart.set(Calendar.DAY_OF_YEAR, start.get(Calendar.DAY_OF_YEAR));
		end.set(Calendar.YEAR, start.get(Calendar.YEAR));
		end.set(Calendar.DAY_OF_YEAR, start.get(Calendar.DAY_OF_YEAR));
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);

		filterTime(newstart, end);
	}
	
	//filters by week
	public void filterWeek(Calendar start) throws ParseException {
		Calendar end = new GregorianCalendar();
		Calendar newstart = new GregorianCalendar();
		
		newstart.set(Calendar.YEAR, start.get(Calendar.YEAR));
		newstart.set(Calendar.WEEK_OF_YEAR, start.get(Calendar.WEEK_OF_YEAR));
		end.set(Calendar.YEAR, start.get(Calendar.YEAR));
		end.set(Calendar.WEEK_OF_YEAR, start.get(Calendar.WEEK_OF_YEAR));
		end.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);

		filterTime(newstart, end);
	}
	
	//filters by month
	public void filterMonth(Calendar start) throws ParseException {
		Calendar end = new GregorianCalendar();
		Calendar newstart = new GregorianCalendar();
		
		newstart.set(Calendar.YEAR, start.get(Calendar.YEAR));
		newstart.set(Calendar.MONTH, start.get(Calendar.MONTH));
		end.set(Calendar.YEAR, start.get(Calendar.YEAR));
		end.set(Calendar.MONTH, start.get(Calendar.MONTH)+1);
		
		filterTime(newstart, end);
	}

}
