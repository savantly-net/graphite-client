package net.savantly.graphite;

import java.util.Collection;

public interface CarbonSender {
	void sendToCarbon(CarbonMetric metric);
	void sendToCarbon(Collection<CarbonMetric> metrics);
}
