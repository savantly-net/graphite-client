package net.savantly.graphite;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.junit.Test;

import net.savantly.graphite.impl.SimpleCarbonMetric;

public class GraphiteClientFactoryTest {

	@Test
	public void testWithNoParameters() throws UnknownHostException, SocketException {
		GraphiteClient client = GraphiteClientFactory.defaultGraphiteClient();
		
		long epoch = System.currentTimeMillis()/1000;
		CarbonMetric metric = new SimpleCarbonMetric("test.one", "123", epoch);
		client.saveCarbonMetrics(metric);
	}

	@Test
	public void testWithHostParameter() throws UnknownHostException, SocketException {
		GraphiteClient client = GraphiteClientFactory.defaultGraphiteClient("localhost");
		
		long epoch = System.currentTimeMillis()/1000;
		CarbonMetric metric = new SimpleCarbonMetric("test.one", "123", epoch);
		client.saveCarbonMetrics(metric);
	}

	@Test
	public void testWithHostAndPortParameters() throws UnknownHostException, SocketException {
		GraphiteClient client = GraphiteClientFactory.defaultGraphiteClient("localhost", 2003);
		
		long epoch = System.currentTimeMillis()/1000;
		CarbonMetric metric = new SimpleCarbonMetric("test.one", "123", epoch);
		client.saveCarbonMetrics(metric);
	}

	/**
	 * You can bind the source IP
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	@Test
	public void testWithAllParameters() throws UnknownHostException, SocketException {
		GraphiteClient client = GraphiteClientFactory.defaultGraphiteClient("localhost", 2003, "0.0.0.0");
		
		long epoch = System.currentTimeMillis()/1000;
		CarbonMetric metric = new SimpleCarbonMetric("test.one", "123", epoch);
		client.saveCarbonMetrics(metric);
	}


}
