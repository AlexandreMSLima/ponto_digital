package com.github.jimsp.pontodigital;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.dto.Workload;
import com.github.jimsp.pontodigital.functions.DateFormat;

public class MockData {

	public static PontoDigitalDto createPontoDigitalDto() {
		return PontoDigitalDto.builder().employees(Arrays.asList(createEmployer()))
				.periodStart(DateFormat.formart().apply(createEntrie(2018, 06, 21)))
				.today(DateFormat.formart().apply(createEntrie(2018, 06, 21))).build();
	}

	public static Employer createEmployer() {
		return Employer //
				.builder() //
				.name("ALEXANDRE MORAES") //
				.pisNumber("0101010101001") //
				.workload(createWorkLoad()) //
				.entries(createEntries()) //
				.build();
	}

	public static Workload createWorkLoad() {
		return Workload //
				.builder() //
				.workloadInMinutes(480) //
				.minimumRestIntervalInMinutes(60) //
				.build();
	}

	public static List<String> createEntries() {
		return Arrays.asList( //
				DateFormat.formart().apply(createEntrie(2018, 06, 21, 8, 30)), //
				DateFormat.formart().apply(createEntrie(2018, 06, 21, 12, 00)), //
				DateFormat.formart().apply(createEntrie(2018, 06, 21, 13, 00)), //
				DateFormat.formart().apply(createEntrie(2018, 06, 21, 17, 30)) //
		);
	}

	public static Date createEntrie(final int year, final int month, final int dayOfMonth, final int hour,
			final int minute) {
		final Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(year, month, dayOfMonth, hour, minute);
		return calendar.getTime();
	}

	public static Date createEntrie(final int year, final int month, final int dayOfMonth) {
		final Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(year, month, dayOfMonth);
		return calendar.getTime();
	}
}
