package net.savantly.graphite.query.impl;

import net.savantly.graphite.query.Target;

public class TargetImpl implements Target {
	
	private String value;

	public TargetImpl(String value) {
		this.value = value;
	}

	@Override
	public String value() {
		return value;
	}

}
