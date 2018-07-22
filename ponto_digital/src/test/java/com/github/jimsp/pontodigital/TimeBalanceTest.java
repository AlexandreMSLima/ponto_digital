package com.github.jimsp.pontodigital;

import static com.github.jimsp.pontodigital.MockData.createEmployer;

import org.junit.Assert;
import org.junit.Test;

import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.functions.TimeBalance;

public class TimeBalanceTest {

	private TimeBalance timeBalance = TimeBalance.create();
	
	@Test
	public void testNoBalanceTime() {
		final Employer employer = createEmployer();
		final Integer balance = timeBalance.apply(employer, 480, 60);
		Assert.assertEquals(Integer.valueOf(0), balance);
	}
	
	@Test
	public void testWithoutIntervalBalanceTime() {
		final Employer employer = createEmployer();
		final Integer balance = timeBalance.apply(employer, 480, 0);
		Assert.assertEquals(Integer.valueOf(60), balance);
	}
	
	@Test
	public void testMinusWorkExpected() {
		final Employer employer = createEmployer();
		final Integer balance = timeBalance.apply(employer, 420, 60);
		Assert.assertEquals(Integer.valueOf(-60), balance);
	}
	
	@Test
	public void testMajorIntervalBalanceTime() {
		final Employer employer = createEmployer();
		final Integer balance = timeBalance.apply(employer, 480, 120);
		Assert.assertEquals(Integer.valueOf(-60), balance);
	}
	
	@Test
	public void testMajorWorkExpected() {
		final Employer employer = createEmployer();
		final Integer balance = timeBalance.apply(employer, 540, 60);
		Assert.assertEquals(Integer.valueOf(60), balance);
	}
	
	@Test
	public void testMajorWorkExpectedAndMijorInterval() {
		final Employer employer = createEmployer();
		final Integer balance = timeBalance.apply(employer, 540, 0);
		Assert.assertEquals(Integer.valueOf(120), balance);
	}
}
