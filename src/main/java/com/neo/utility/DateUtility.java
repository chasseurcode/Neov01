package com.neo.utility;

import java.util.Calendar;
import java.util.Date;

public class DateUtility {

	@SuppressWarnings("unused")
	private static Date getDate(String year) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.parseInt(year));
			return calendar.getTime();
		} catch (NumberFormatException nfe) {
			return null;
		}
	}
}
