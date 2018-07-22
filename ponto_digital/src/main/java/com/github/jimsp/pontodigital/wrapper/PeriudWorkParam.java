package com.github.jimsp.pontodigital.wrapper;

import java.math.BigInteger;
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

	public Integer getTimeWorkMinutes() {
		return BigInteger.valueOf((exit.getTime() - entry.getTime()) / (1_000 * 60)).intValue();
	}
}
