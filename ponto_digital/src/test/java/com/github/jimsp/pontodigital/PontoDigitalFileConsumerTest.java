package com.github.jimsp.pontodigital;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
	private PontoDigitalFileConsumer pontoDigitalFileConsumer = PontoDigitalFileConsumer.create(new ObjectMapper(),
			pontoDigitalFluxe);

	@Test(expected = PontoDigitalWithProblemFileException.class)
	public void testWithProblem() {
		pontoDigitalFileConsumer.accept(new ByteArrayInputStream(new byte[] { 0x00, 0x01 }), System.out);
	}

	@Test
	public void test() throws FileNotFoundException {
		pontoDigitalFileConsumer.accept(new FileInputStream("input/input.json"), System.out);
	}
	
	@Test(expected=PontoDigitalValidationException.class)
	public void testInvalidData() throws FileNotFoundException {
		pontoDigitalFileConsumer.accept(new FileInputStream("input/invalid.json"), System.out);
	}
	
	@Test(expected=PontoDigitalValidationException.class)
	public void testInvalidNestedData() throws FileNotFoundException {
		pontoDigitalFileConsumer.accept(new FileInputStream("input/invalidNested.json"), System.out);
	}

	@Test
	public void printDistinctsDay() throws JsonParseException, JsonMappingException, IOException {
		final PontoDigitalFluxe pontoDigitalFluxe = PontoDigitalFluxe.create();

		final PontoDigitalFileConsumer pontoDigitalFileConsumer = PontoDigitalFileConsumer.create(pontoDigitalFluxe);

		final PontoDigitalDto pontoDigitalDto = pontoDigitalFileConsumer.read(new FileInputStream("input/input.json"));

		final Employer employer = pontoDigitalDto //
				.getEmployees() //
				.get(0);

		Assert.assertEquals(17L, DistinctDaysStream //
				.of(employer) //
				.count());
	}
}
