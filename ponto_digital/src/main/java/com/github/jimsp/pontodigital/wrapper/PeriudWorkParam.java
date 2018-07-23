package com.github.jimsp.pontodigital.wrapper;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class PeriudWorkParam {
	private final Date entry;
	private final Date exit;
	private final Integer timeWorkMinutes;
}
