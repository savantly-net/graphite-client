# Graphite Client
[![Build Status](https://travis-ci.org/graphite-client/graphite-client.svg?branch=master)](https://travis-ci.org/savantly-net/graphite-client)

Use in Maven project -

```
	<dependency>
		<groupId>net.savantly</groupId>
		<artifactId>graphite-client</artifactId>
		<version>1.0.0-RELEASE</version>
	</dependency>
```


## Example 

```java

	@Test
	public void testWithHostAndPortParameters() throws UnknownHostException, SocketException {
		GraphiteClient client = GraphiteClientFactory.defaultGraphiteClient("localhost", 2003);
		
		long epoch = System.currentTimeMillis()/1000;
		CarbonMetric metric = new SimpleCarbonMetric("test.one", "123", epoch);
		client.saveCarbonMetrics(metric);
	}
```