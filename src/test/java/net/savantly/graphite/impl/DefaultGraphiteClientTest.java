package net.savantly.graphite.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.savantly.graphite.CarbonFactory;
import net.savantly.graphite.CarbonMetric;
import net.savantly.graphite.GraphiteClient;
import net.savantly.graphite.MockCarbonServer;

public class DefaultGraphiteClientTest {
	

	private String graphiteHost = "localhost";
	private int carbonPort = 2003;
	private String sourceIp = "0.0.0.0";

	@Test
	public void testSaveOne() throws IOException, InterruptedException {
		MockCarbonServer server = new MockCarbonServer(1, carbonPort);
		server.start();
		GraphiteClient client = new DefaultGraphiteClient(CarbonFactory.getDefaultCarbonSender(graphiteHost, carbonPort, sourceIp));
		
		long epoch = System.currentTimeMillis()/1000;
		CarbonMetric metric = new SimpleCarbonMetric("test.one", "123", epoch);
		client.saveCarbonMetrics(metric);
		
		server.stop(10000);
		Assert.assertEquals(1, server.getHistory().size());
		server = null;
	}
	
	@Test
	public void testSaveMany() throws IOException, InterruptedException {
		int testSize = 50;
		MockCarbonServer server = new MockCarbonServer(1, carbonPort);
		server.start();
		
		GraphiteClient client = new DefaultGraphiteClient(CarbonFactory.getDefaultCarbonSender(graphiteHost, carbonPort, sourceIp));
		long epoch = System.currentTimeMillis()/1000;
		List<CarbonMetric> metrics = new ArrayList<CarbonMetric>();
		for(int i = 0; i < testSize; i++){
			metrics.add(new SimpleCarbonMetric("test.one", "123", epoch));
		}
		
		client.saveCarbonMetrics(metrics);
		
		server.stop(10000);
		Assert.assertEquals(1, server.getHistory().size());
		String[] sentences = server.getHistory().get(0).split("\n");
		Assert.assertEquals(testSize, sentences.length-1);
		server = null;
	}


}
