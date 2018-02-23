package net.savantly.graphite.query.impl;

import net.savantly.graphite.query.GraphiteTimeUnit;
import net.savantly.graphite.query.Until;

public class UntilImpl implements Until {
	
	private String value = "now";
	
	public UntilImpl() { }
	
	public UntilImpl(String until) {
		this.value = until;
	}
	
	public UntilImpl(int value, GraphiteTimeUnit unit) {
		this.value = String.format("%i%s", value, unit.value());
	}


	@Override
	public String value() {
		return this.value;
	}

}
