package net.savantly.graphite.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.savantly.graphite.CarbonMetric;
import net.savantly.graphite.CarbonSender;

public class MockCarbonSender implements CarbonSender {
	private static final Logger log = LoggerFactory.getLogger(MockCarbonSender.class);

	@Override
	public void sendToCarbon(CarbonMetric metric) {
		log.info(String.format("Received CarbonMetric: %s", metric));
	}

	@Override
	public void sendToCarbon(Collection<CarbonMetric> metrics) {
		for (CarbonMetric carbonMetric : metrics) {
			log.info(String.format("Received CarbonMetric: %s", carbonMetric));
		}
	}

}
