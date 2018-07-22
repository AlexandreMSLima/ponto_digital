package com.github.jimsp.pontodigital;

import static com.github.jimsp.pontodigital.MockData.createEmployer;

import org.junit.Assert;
import org.junit.Test;

import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.functions.TimeWorkedDuringThePeriod;

public class TimeWorkedDuringThePeriodTest {

private TimeWorkedDuringThePeriod timeWorkedDuringThePeriod = TimeWorkedDuringThePeriod.create();
	
	@Test
	public void test() {
		final Employer employer = createEmployer();
		final Integer minutes = timeWorkedDuringThePeriod.apply(employer);
		Assert.assertEquals(Integer.valueOf(480), minutes);
	}
}
