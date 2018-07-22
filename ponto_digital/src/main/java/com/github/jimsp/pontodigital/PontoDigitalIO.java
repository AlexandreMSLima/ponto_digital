package com.github.jimsp.pontodigital;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder=true)
public class PontoDigitalIO {

	private final File input;
	private final File output;
}
