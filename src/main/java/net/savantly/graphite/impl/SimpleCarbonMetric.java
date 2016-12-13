package net.savantly.graphite.impl;

import net.savantly.graphite.CarbonMetric;

public class SimpleCarbonMetric implements CarbonMetric {
	
	String metricName;
	String value;
	long epoch;
	

	/**
	 * @param metricName
	 * @param value
	 * @param epoch
	 */
	public SimpleCarbonMetric(String metricName, String value, long epoch) {
		this.metricName = metricName;
		this.value = value;
		this.epoch = epoch;
	}

	@Override
	public String getMetricName() {
		return metricName;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public long getEpoch() {
		return epoch;
	}

	@Override
	public String toString() {
		return "SimpleCarbonMetric [metricName=" + metricName + ", value=" + value + ", epoch=" + epoch + "]";
	}

	
}
