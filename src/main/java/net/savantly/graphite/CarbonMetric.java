package net.savantly.graphite;

public interface CarbonMetric {
	String getMetricName();
	String getValue();
	long getEpoch();
}
