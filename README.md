[![Build Status](https://travis-ci.org/savantly-net/graphite-client.svg?branch=master)](https://travis-ci.org/savantly-net/graphite-client)

# Graphite Client


Use in Maven project -

```
	<dependency>
		<groupId>net.savantly</groupId>
		<artifactId>graphite-client</artifactId>
		<version>2.0.0-RELEASE</version>
	</dependency>
```


## Reporting Example 

	GraphiteClient client = GraphiteClientFactory.defaultGraphiteClient("localhost", 2003);
	
	long epoch = System.currentTimeMillis()/1000;
	CarbonMetric metric = new SimpleCarbonMetric("test.one", "123", epoch);
	client.saveCarbonMetrics(metric);



## Query Example -  
	
	String graphiteHost = "localhost";
	QueryableGraphiteClient client = GraphiteClientFactory.queryableGraphiteClient(graphiteHost);
	
	String target = "constantLine(123.456)";
	
	JsonFormatter formatter = new JsonFormatter();
	GraphiteQueryBuilder<JsonNode> builder = new GraphiteQueryBuilder<>(formatter);
	GraphiteQuery<JsonNode> query = builder.setTarget(target).build();
	
	JsonNode results = client.query(query);
