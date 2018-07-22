package com.github.jimsp.pontodigital;

import java.util.List;

import org.junit.Test;

import com.github.jimsp.pontodigital.functions.PontoDigitalFluxe;
import com.github.jimsp.pontodigital.report.PontoDigitalReport;

public class PontoDigitalFluxeTest {
	
	private PontoDigitalFluxe pontoDigitalFluxe = PontoDigitalFluxe.create();
	
	@Test
	public void test() {
		final List<PontoDigitalReport> result = pontoDigitalFluxe.apply(MockData.createPontoDigitalDto());
		result.forEach(action->System.out.println(result));
	}
}
