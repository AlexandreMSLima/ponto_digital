package com.github.jimsp.pontodigital;

import org.junit.Assert;
import org.junit.Test;

import com.github.jimsp.pontodigital.functions.DistinctDaysStream;

public class DistinctDaysStreamTest {
	
	@Test
	public void test() {
		Assert.assertEquals(1L, DistinctDaysStream
			.of(MockData.createEmployer())
			.count());
	}
}
