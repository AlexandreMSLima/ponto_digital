package com.github.jimsp.pontodigital.dto;

public enum Day {
	
	mon("mon"), tue("tue"), wed("wed"), thu("thu"), fri("fri"), sat("sat"), sun("sun");
	
	private final String abreviation;
	
	private Day(final String abreviation) {
		this.abreviation = abreviation;
	}

	public String getAbreviation() {
		return abreviation;
	}
}
