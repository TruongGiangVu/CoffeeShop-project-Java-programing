package bus;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.*;
import java.text.*;
public class DateExe {
	final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	final static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static int getDaysOfMonth(int month, int year) {
		switch(month) {
		case 2: return DateExe.isLeapYear(year)?29:28;
		case 4|6|9|11: return 30;
		default: return 31;
		}
	}
	public static boolean isLeapYear(int year) {
		return year%400==0 || (year%100!=0 && year%4 == 0);
	}
	public static int compares(String dateStr1, String dateStr2) {
		LocalDate x = LocalDate.parse(dateStr1, df);
		LocalDate y = LocalDate.parse(dateStr2, df);
		return compares(x,y);
	}
	public static Date convertStringToDate(String date) {
		Date d = null;
		try {
		   d = sdf.parse(date);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return d;
	}
	public static LocalDate convertDateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	public static String convertDateToString(Date date) {
		return df.format(convertDateToLocalDate(date));
	}
	public static int compares(LocalDate x, LocalDate y) {
		/*
		if(x.getYear() > y.getYear()) return 1;
			else if(x.getYear() < y.getYear()) return -1;
			else if(x.getMonthValue() > y.getMonthValue()) return 1;
			else if(x.getMonthValue() < y.getMonthValue()) return -1;
			else if(x.getDayOfMonth() > y.getDayOfMonth()) return 1;
			else if(x.getDayOfMonth() < y.getDayOfMonth()) return -1;
			else return 0;
			*/
		int cmp = (x.getYear() - y.getYear());
	    if (cmp == 0) {
	        cmp = (x.getMonthValue() - y.getMonthValue());
	        if (cmp == 0) {
	            cmp = (x.getDayOfMonth()-y.getDayOfMonth());
	        }
	    }
	    return cmp;
	}
	public static boolean isValidDate(String dateStr) throws Exception{
		LocalDate date = LocalDate.parse(dateStr, df);
		return isValidDate(date);
	}
	public static boolean isValidDate(LocalDate date) {
		return date.getDayOfMonth() > 0 && date.getDayOfMonth() < DateExe.getDaysOfMonth(date.getMonthValue(), date.getYear());
	}
}
