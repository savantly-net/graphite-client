package net.savantly.graphite.query;

public enum GraphiteTimeUnit {
	
	SECONDS("s"),
	MINUTES("min"),
	HOURS("h"),
	DAYS("d"),
	WEEKS("w"),
	MONTHS("mon"),
	YEARS("y");
	
	private final String value;
	public String value() {
		return this.value;
	}
	
	GraphiteTimeUnit(String value){
		this.value = value;
	}

}
