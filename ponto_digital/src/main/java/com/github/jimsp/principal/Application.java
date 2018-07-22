package com.github.jimsp.principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.github.jimsp.pontodigital.PontoDigitalFileConsumer;
import com.github.jimsp.pontodigital.dto.PontoDigitalDto;
import com.github.jimsp.pontodigital.exceptions.PontoDigitalWithProblemFileException;
import com.github.jimsp.pontodigital.functions.PontoDigitalFluxe;
import com.github.jimsp.pontodigital.report.PontoDigitalReport;

public final class Application {

	public static void main(final String[] args) {
		final Application application = new Application();
		application.execute();
	}

	private final Function<PontoDigitalDto, List<PontoDigitalReport>> pontoDigitalFluxe = PontoDigitalFluxe.create();
	private final BiConsumer<InputStream, OutputStream> pontoDigitalFileConsumer = PontoDigitalFileConsumer.create(pontoDigitalFluxe);

	public void execute() {
		try {
			pontoDigitalFileConsumer.accept(Files.newInputStream(Paths.get("input", "input.json")), Files.newOutputStream(Paths.get("output", "output.json")));
		} catch (IOException e) {
			throw new PontoDigitalWithProblemFileException(e);
		}
	}
}
