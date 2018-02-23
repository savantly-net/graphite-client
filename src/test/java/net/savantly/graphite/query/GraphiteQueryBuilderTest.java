package net.savantly.graphite.query;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import net.savantly.graphite.GraphiteClientFactory;
import net.savantly.graphite.QueryableGraphiteClient;
import net.savantly.graphite.query.fomat.JsonFormatter;

public class GraphiteQueryBuilderTest {
	
	@Test
	public void testDefaults() {
		String target = "constantLine(123.456)";
		JsonFormatter formatter = new JsonFormatter();
		GraphiteQueryBuilder<JsonNode> builder = new GraphiteQueryBuilder<>(formatter);
		GraphiteQuery query = builder.setTarget(target).build();
		
		Assert.assertTrue("format should be the same", query.format().equals(builder.getFormat()));
		Assert.assertTrue("from should be the same", query.from().equals(builder.getFrom()));
		Assert.assertTrue("target should be the same", query.target().equals(builder.getTarget()));
		Assert.assertTrue("format should be the same", query.until().equals(builder.getUntil()));
	}
	
	private void example() throws UnknownHostException, SocketException {
		
		String graphiteHost = "localhost";
		QueryableGraphiteClient client = GraphiteClientFactory.queryableGraphiteClient(graphiteHost);
		
		String target = "constantLine(123.456)";
		
		JsonFormatter formatter = new JsonFormatter();
		GraphiteQueryBuilder<JsonNode> builder = new GraphiteQueryBuilder<>(formatter);
		GraphiteQuery<JsonNode> query = builder.setTarget(target).build();
		
		JsonNode results = client.query(query);
	
	}

}
