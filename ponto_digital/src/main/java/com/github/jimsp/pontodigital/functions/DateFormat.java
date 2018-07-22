package com.github.jimsp.pontodigital.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

public final class DateFormat {

	public static Function<String, Date> parseDate() {
		return date -> {
			try {
				return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		};
	}

	public static Function<Date, String> formart() {
		return date -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date);
	}
}
