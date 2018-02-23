package net.savantly.graphite.query.impl;

import net.savantly.graphite.query.From;
import net.savantly.graphite.query.GraphiteTimeUnit;

public class FromImpl implements From {
	
	String value = "-24h";
	
	public FromImpl() { }
	
	public FromImpl(String value) {
		this.value = value;
	}
	
	public FromImpl(int value, GraphiteTimeUnit unit) {
		this.value = String.format("%i%s", value, unit.value());
	}

	@Override
	public String value() {
		return this.value;
	}

}
