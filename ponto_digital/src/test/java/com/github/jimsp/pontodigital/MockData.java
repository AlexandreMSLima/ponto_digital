package com.github.jimsp.pontodigital;
import static com.github.jimsp.pontodigital.FunctionalCatalog.$;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.dto.Workload;

public class MockData {

	public static PontoDigitalDto createPontoDigitalDto() {
		return PontoDigitalDto.builder().employees(Arrays.asList(createEmployer()))
				.periodStart($.formartDateTime().apply(createEntrie(2018, 06, 21)))
				.today($.formartDateTime().apply(createEntrie(2018, 06, 21))).build();
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
	
	public static Employer createEmployerNextDay() {
		return Employer //
				.builder() //
				.name("ALEXANDRE MORAES") //
				.pisNumber("0101010101001") //
				.workload(createWorkLoad()) //
				.entries(createEntriesNextDay()) //
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
				$.formartDateTime().apply(createEntrie(2018, 06, 21, 8, 30)), //
				$.formartDateTime().apply(createEntrie(2018, 06, 21, 12, 0)), //
				$.formartDateTime().apply(createEntrie(2018, 06, 21, 13, 0)), //
				$.formartDateTime().apply(createEntrie(2018, 06, 21, 17, 30)) //
		);
	}
	
	public static List<String> createEntriesNextDay() {
		return Arrays.asList( //
				$.formartDateTime().apply(createEntrie(2018, 06, 21, 23, 30)), //
				$.formartDateTime().apply(createEntrie(2018, 06, 22, 3, 0)), //
				$.formartDateTime().apply(createEntrie(2018, 06, 22, 4, 0)), //
				$.formartDateTime().apply(createEntrie(2018, 06, 22, 8, 30)) //
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
