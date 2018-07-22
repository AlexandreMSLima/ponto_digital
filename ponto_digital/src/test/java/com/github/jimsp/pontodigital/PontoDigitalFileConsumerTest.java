package com.github.jimsp.pontodigital;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.functions.DistinctDaysStream;

public class PontoDigitalFileConsumerTest {

	private PontoDigitalFluxe pontoDigitalFluxe = PontoDigitalFluxe.create();
	private PontoDigitalIO pontoDigitalIO = PontoDigitalIO.builder().input(new File("input")).output(new File("output"))
			.build();
	private PontoDigitalFileConsumer pontoDigitalFileConsumer = PontoDigitalFileConsumer.create(new ObjectMapper(),
			pontoDigitalFluxe, pontoDigitalIO);

	@Test(expected=PontoDigitalWithProblemFileException.class)
	public void testWithProblem() {
		pontoDigitalFileConsumer.accept("", "output.json");
	}
	
	@Test
	public void test() {
		pontoDigitalFileConsumer.accept("input.json", "output.json");
		System.out.println(Arrays.toString(pontoDigitalIO.getOutput().listFiles()));
	}

	@Test
	public void printDistinctsDay() throws JsonParseException, JsonMappingException, IOException {
		final PontoDigitalFluxe pontoDigitalFluxe = PontoDigitalFluxe.create();
		final PontoDigitalIO pontoDigitalIO = PontoDigitalIO.builder().input(new File("input"))
				.output(new File("output")).build();
		final PontoDigitalFileConsumer pontoDigitalFileConsumer = PontoDigitalFileConsumer.create(pontoDigitalFluxe,
				pontoDigitalIO);

		final PontoDigitalDto pontoDigitalDto = pontoDigitalFileConsumer.read("input.json");

		final Employer employer = pontoDigitalDto //
				.getEmployees() //
				.get(0);

		Assert.assertEquals(17L, DistinctDaysStream //
				.of(employer) //
				.count());
	}
}
