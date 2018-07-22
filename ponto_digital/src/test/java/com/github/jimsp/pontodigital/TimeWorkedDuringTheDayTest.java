package com.github.jimsp.pontodigital;

import static com.github.jimsp.pontodigital.MockData.createEmployer;
import static com.github.jimsp.pontodigital.MockData.createEmployerNextDay;
import static com.github.jimsp.pontodigital.MockData.createEntrie;

import org.junit.Assert;
import org.junit.Test;

import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.functions.ItsTheSameDay;
import com.github.jimsp.pontodigital.functions.TimeWorkedDuringTheDay;

public class TimeWorkedDuringTheDayTest {

	private TimeWorkedDuringTheDay timeWorkedDuringTheDay = TimeWorkedDuringTheDay.create();

	@Test
	public void testOtherYear() {
		final Employer employer = createEmployer();
		final ItsTheSameDay itsTheSameDay = ItsTheSameDay.create(createEntrie(2017, 06, 21));
		final Integer minutes = timeWorkedDuringTheDay.apply(employer, itsTheSameDay);
		Assert.assertEquals(Integer.valueOf(0), minutes);
	}

	@Test
	public void testOtherMonth() {
		final Employer employer = createEmployer();
		final ItsTheSameDay itsTheSameDay = ItsTheSameDay.create(createEntrie(2018, 07, 21));
		final Integer minutes = timeWorkedDuringTheDay.apply(employer, itsTheSameDay);
		Assert.assertEquals(Integer.valueOf(0), minutes);
	}

	@Test
	public void testOtherDay() {
		final Employer employer = createEmployer();
		final ItsTheSameDay itsTheSameDay = ItsTheSameDay.create(createEntrie(2017, 06, 20));
		final Integer minutes = timeWorkedDuringTheDay.apply(employer, itsTheSameDay);
		Assert.assertEquals(Integer.valueOf(0), minutes);
	}

	@Test
	public void testSameDayMonthAndYear() {
		final Employer employer = createEmployer();
		final ItsTheSameDay itsTheSameDay = ItsTheSameDay.create(createEntrie(2018, 06, 21));
		final Integer minutes = timeWorkedDuringTheDay.apply(employer, itsTheSameDay);
		Assert.assertEquals(Integer.valueOf(480), minutes);
	}
	
	@Test
	public void testExitNextDay() {
		final Employer employer = createEmployerNextDay();
		final ItsTheSameDay itsTheSameDay = ItsTheSameDay.create(createEntrie(2018, 06, 21));
		final Integer minutes = timeWorkedDuringTheDay.apply(employer, itsTheSameDay);
		Assert.assertEquals(Integer.valueOf(29), minutes);
	}
}
