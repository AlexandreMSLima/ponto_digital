package com.github.jimsp.pontodigital;

import org.junit.Assert;
import org.junit.Test;

import com.github.jimsp.pontodigital.functions.ItsTheSameDay;
import com.github.jimsp.pontodigital.functions.TimeIntervalDuringTheDay;

public class TimeIntervalDuringTheDayTest {
	
	private TimeIntervalDuringTheDay timeIntervalDuringTheDay = TimeIntervalDuringTheDay.create();
	
	@Test
	public void test() {
		Assert.assertEquals(Integer.valueOf(60), timeIntervalDuringTheDay.apply(MockData.createEmployer(), ItsTheSameDay.create(MockData.createEntrie(2018, 6, 21))));
	}

}
