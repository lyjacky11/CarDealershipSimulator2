import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarTester 
{

	public static void main(String[] args) 
	{
	    // Example usage of date format and Calendar. Source:
		//https://www.mkyong.com/java/java-date-and-calendar-examples/
		 
		Calendar calendar;
		calendar = new GregorianCalendar(2013,1,28,13,24,56);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
		
		int year       = calendar.get(Calendar.YEAR);
		int month      = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);

		int hour       = calendar.get(Calendar.HOUR);        // 12 hour clock
		int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
		int minute     = calendar.get(Calendar.MINUTE);
		int second     = calendar.get(Calendar.SECOND);
		int millisecond= calendar.get(Calendar.MILLISECOND);

		System.out.println(sdf.format(calendar.getTime()));

		System.out.println("year \t\t: " + year);
		System.out.println("month \t\t: " + month);
		System.out.println("dayOfMonth \t: " + dayOfMonth);
		System.out.println("dayOfWeek \t: " + dayOfWeek);
		System.out.println("weekOfYear \t: " + weekOfYear);
		System.out.println("weekOfMonth \t: " + weekOfMonth);

		System.out.println("hour \t\t: " + hour);
		System.out.println("hourOfDay \t: " + hourOfDay);
		System.out.println("minute \t\t: " + minute);
		System.out.println("second \t\t: " + second);
		System.out.println("millisecond \t: " + millisecond);
		

		// Example of setting date manually.
		calendar = new GregorianCalendar(2013,1,28,13,24,56);
		System.out.println("#1. " + sdf.format(calendar.getTime()));

		// Example of updating a date
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.MINUTE, 33);

		// Example of getting maximum number of days in a month
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
		
		System.out.println("#2. " + sdf.format(calendar.getTime()));
		
		
		
		// Example of adding or subtracting from a date
		sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");

		calendar = new GregorianCalendar(2013,10,28);
		System.out.println("Date : " + sdf.format(calendar.getTime()));

		// Add one month
		calendar.add(Calendar.MONTH, 1);
		System.out.println("Date : " + sdf.format(calendar.getTime()));

		// Subtract 10 days
		calendar.add(Calendar.DAY_OF_MONTH, -10);
		System.out.println("Date : " + sdf.format(calendar.getTime()));
		
		
	}

}
