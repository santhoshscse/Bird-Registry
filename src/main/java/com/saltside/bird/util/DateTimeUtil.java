package com.saltside.bird.util;

import java.util.Date;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author santhoshsrinivasan
 *
 */
public class DateTimeUtil {
	private static DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-MM-dd");

	public static String formatDate(Date date) {
		return dtf.print(date.getTime());
	}

	public static void main(String[] args) {
		System.out.println(formatDate(new Date()));
	}
}
