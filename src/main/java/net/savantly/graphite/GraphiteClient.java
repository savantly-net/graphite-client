package net.savantly.graphite;

import java.util.Collection;

public interface GraphiteClient {
	void saveCarbonMetrics(CarbonMetric carbonMetric);
	void saveCarbonMetrics(Collection<CarbonMetric> carbonMetrics);
}
