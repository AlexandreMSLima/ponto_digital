package com.github.jimsp.pontodigital;

import java.util.List;

import org.junit.Test;

public class PontoDigitalFluxeTest {
	
	private PontoDigitalFluxe pontoDigitalFluxe = PontoDigitalFluxe.create();
	
	@Test
	public void test() {
		final List<PontoDigitalReport> result = pontoDigitalFluxe.processDto(MockData.createPontoDigitalDto());
		result.forEach(action->System.out.println(result));
	}
}
