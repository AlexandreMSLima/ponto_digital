package com.github.jimsp.pontodigital;

import static com.github.jimsp.pontodigital.FunctionalCatalog.$;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.jimsp.pontodigital.dto.Employer;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.exceptions.PontoDigitalValidationException;
import com.github.jimsp.pontodigital.exceptions.PontoDigitalWithProblemFileException;
import com.github.jimsp.pontodigital.functions.DistinctDaysStream;
import com.github.jimsp.pontodigital.functions.PontoDigitalRead;

public class PontoDigitalFileConsumerTest {

	private PontoDigitalRead pontoDigitalRead = PontoDigitalRead.create();
	private BiConsumer<InputStream, OutputStream> pontoDigitalFileConsumer = $.pontoDigitalFileConsumer();

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
		final PontoDigitalDto pontoDigitalDto = pontoDigitalRead.apply(new FileInputStream("input/input.json"));

		final Employer employer = pontoDigitalDto //
				.getEmployees() //
				.get(0);

		Assert.assertEquals(17L, DistinctDaysStream //
				.of(employer) //
				.count());
	}
}
