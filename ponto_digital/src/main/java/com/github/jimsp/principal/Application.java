package com.github.jimsp.principal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.jimsp.pontodigital.PontoDigitalFileConsumer;
import com.github.jimsp.pontodigital.PontoDigitalFluxe;
import com.github.jimsp.pontodigital.PontoDigitalWithProblemFileException;

public final class Application {

	public static void main(final String[] args) {
		final Application application = new Application();
		application.execute();
	}

	private final PontoDigitalFluxe pontoDigitalFluxe = PontoDigitalFluxe.create();
	private final PontoDigitalFileConsumer pontoDigitalFileConsumer = PontoDigitalFileConsumer.create(pontoDigitalFluxe);

	public void execute() {
		try {
			pontoDigitalFileConsumer.accept(Files.newInputStream(Paths.get("input", "input.json")), Files.newOutputStream(Paths.get("output", "output.json")));
		} catch (IOException e) {
			throw new PontoDigitalWithProblemFileException(e);
		}
	}
}
