package com.github.jimsp.principal;

import static com.github.jimsp.pontodigital.FunctionalCatalog.$;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Application {

	public static void main(final String[] args) {
		try {
			 new Application().execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void execute() throws IOException {
		$ //
		.pontoDigitalFileConsumer() //
		.accept(Files //
				.newInputStream(Paths.get("input", "input.json")), //
				Files //
						.newOutputStream(Paths.get("output", "output.json")));
	}
}
