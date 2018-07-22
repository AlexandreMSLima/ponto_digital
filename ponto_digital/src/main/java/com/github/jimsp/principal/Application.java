package com.github.jimsp.principal;

import java.io.File;

import com.github.jimsp.pontodigital.PontoDigitalFileConsumer;
import com.github.jimsp.pontodigital.PontoDigitalFluxe;
import com.github.jimsp.pontodigital.PontoDigitalIO;

public final class Application {

	private static final PontoDigitalIO PDIO = createPontoDigitalIO();

	private static PontoDigitalIO createPontoDigitalIO() {
		final File input = new File("input");
		if (!input.exists()) {
			input.mkdir();
		}

		final File output = new File("output");
		if (!output.exists()) {
			output.mkdir();
		}

		return PontoDigitalIO //
				.builder() //
				.input(input) //
				.output(output) //
				.build();
	}

	public static void main(final String[] args) {
		final Application application = new Application();
		application.execute();
	}

	private final PontoDigitalFluxe pontoDigitalFluxe = PontoDigitalFluxe.create();
	private final PontoDigitalFileConsumer pontoDigitalFileConsumer = PontoDigitalFileConsumer.create(pontoDigitalFluxe,
			PDIO);

	public void execute() {
		pontoDigitalFileConsumer.accept("input.json", "output.json");
	}
}
