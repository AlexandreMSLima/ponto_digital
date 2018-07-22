package com.github.jimsp.pontodigital;

import static com.github.jimsp.pontodigital.MockData.createEmployer;

import org.junit.Assert;
import org.junit.Test;

import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.functions.TimeBalance;
import com.github.jimsp.pontodigital.wrapper.TimeBalanceParam;

public class TimeBalanceTest {

	private TimeBalance timeBalance = TimeBalance.create();

	@Test
	public void testNoBalanceTime() {
		final Employer employer = createEmployer();
		final Integer balance = timeBalance.apply(employer, TimeBalanceParam //
				.builder() //
				.workDayMinutes(480) //
				.intervalMinutes(60) //
				.build());
		Assert.assertEquals(Integer.valueOf(0), balance);
	}

	@Test
	public void testWithoutIntervalBalanceTime() {
		final Employer employer = createEmployer();
		final Integer balance = timeBalance.apply(employer, TimeBalanceParam //
				.builder() //
				.workDayMinutes(480) //
				.intervalMinutes(0) //
				.build());
		Assert.assertEquals(Integer.valueOf(60), balance);
	}

	@Test
	public void testMinusWorkExpected() {
		final Employer employer = createEmployer();
		final Integer balance = timeBalance.apply(employer, TimeBalanceParam //
				.builder() //
				.workDayMinutes(420) //
				.intervalMinutes(60) //
				.build());
		Assert.assertEquals(Integer.valueOf(-60), balance);
	}

	@Test
	public void testMajorIntervalBalanceTime() {
		final Employer employer = createEmployer();
		final Integer balance = timeBalance.apply(employer, TimeBalanceParam //
				.builder() //
				.workDayMinutes(480) //
				.intervalMinutes(120) //
				.build());
		Assert.assertEquals(Integer.valueOf(-60), balance);
	}

	@Test
	public void testMajorWorkExpected() {
		final Employer employer = createEmployer();
		final Integer balance = timeBalance.apply(employer, TimeBalanceParam //
				.builder() //
				.workDayMinutes(540) //
				.intervalMinutes(60) //
				.build());
		Assert.assertEquals(Integer.valueOf(60), balance);
	}

	@Test
	public void testMajorWorkExpectedAndMijorInterval() {
		final Employer employer = createEmployer();
		final Integer balance = timeBalance.apply(employer, TimeBalanceParam //
				.builder() //
				.workDayMinutes(540) //
				.intervalMinutes(0) //
				.build());
		Assert.assertEquals(Integer.valueOf(120), balance);
	}
}
