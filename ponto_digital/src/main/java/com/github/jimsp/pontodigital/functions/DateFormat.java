package com.github.jimsp.pontodigital.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

public final class DateFormat {

	public static Function<String, Date> parseDateTime() {
		return date -> {
			try {
				return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		};
	}
	
	public static Function<String, Date> parseDate() {
		return date -> {
			try {
				return new SimpleDateFormat("yyyy-MM-dd").parse(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		};
	}

	public static Function<Date, String> formartDateTime() {
		return date -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date);
	}
	
	public static Function<Date, String> formartDate() {
		return date -> new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static Function<Date, Date> parseTimeZeroHour() {
		return date -> parseDateTime().apply(formartDate().apply(date).concat("T00:00:00"));
	}

	public static Function<Date, Date> parseTimeLastHour() {
		return date -> parseDateTime().apply(formartDate().apply(date).concat("T23:59:59"));
	}
}
