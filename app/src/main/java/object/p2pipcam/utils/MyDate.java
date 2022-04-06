package object.p2pipcam.utils;

public class MyDate {
	
	
	int year=0;
	
	int month=0;
	
	int day=0;
	
	int hour=0;
	
	int minute=0;
	
	boolean isRepeatCase=false;
	
	public MyDate(int year, int month, int day, int hour, int minute) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}
	
	
	
	public static String getFormattedDate(MyDate date)
	{
		
		String dateString = "";
		
		
		if(date.day<=9)
		{
			dateString = "0" + date.day;
		}
		else
		{
			dateString = ""+date.day;
		}
		
		if(date.month <= 9)
		{
			
			dateString+= ":0" + date.month;
			
		}
		else
		{
			dateString+= ":" + date.month;
		}
		
		
			dateString+= ":" + date.year;
		
		
		return dateString;
		
		
	}
	
	public static String getFormattedTime(MyDate date)
	{
		
		String dateString = "";
		
		
		if(date.hour<=9)
		{
			dateString = "0" + date.hour;
		}
		else
		{
			dateString = ""+date.hour;
		}
		
		if(date.minute <= 9)
		{
			
			dateString+= ":0" + date.minute;
			
		}
		else
		{
			dateString+= ":" + date.minute;
		}
		
		
			
		
		
		return dateString;
		
		
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
	public boolean isRepeatCase() {
		return isRepeatCase;
	}


	public void setRepeatCase(boolean isRepeatCase) {
		this.isRepeatCase = isRepeatCase;
	}
	
	
	

}
