package net.savantly.graphite;

import java.net.SocketException;
import java.net.UnknownHostException;

import net.savantly.graphite.impl.DefaultGraphiteClient;
import net.savantly.graphite.impl.QueryableGraphiteClientImpl;
import net.savantly.graphite.web.GraphiteWebConfiguration;
import net.savantly.graphite.web.GraphiteWebConfigurationBuilder;

public class GraphiteClientFactory {
	
	/**
	 * Get a GraphiteClient implementation configured for the localhost port 2003
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static GraphiteClient defaultGraphiteClient() throws UnknownHostException, SocketException{
		CarbonSender carbonSender = CarbonFactory.getDefaultCarbonSender("localhost", 2003, "0.0.0.0");
		return new DefaultGraphiteClient(carbonSender);
	}

	/**
	 * Get a GraphiteClient implementation that connects to the specified host on port 2003
	 * @param graphiteHost
	 * The graphite host to connect to
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static GraphiteClient defaultGraphiteClient(String graphiteHost) throws UnknownHostException, SocketException{
		CarbonSender carbonSender = CarbonFactory.getDefaultCarbonSender(graphiteHost, 2003, "0.0.0.0");
		return new DefaultGraphiteClient(carbonSender);
	}
	
	/**
	 * Get a GraphiteClient implementation
	 * @param graphiteHost
	 * The graphite host to connect to
	 * @param carbonPort
	 * The port on the graphite host to connect to
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static GraphiteClient defaultGraphiteClient(String graphiteHost, int carbonPort) throws UnknownHostException, SocketException{
		CarbonSender carbonSender = CarbonFactory.getDefaultCarbonSender(graphiteHost, carbonPort, "0.0.0.0");
		return new DefaultGraphiteClient(carbonSender);
	}
	
	/**
	 * Get a GraphiteClient implementation
	 * @param graphiteHost
	 * The graphite host to connect to
	 * @param carbonPort
	 * The port on the graphite host to connect to
	 * @param sourceIp
	 * The ip to bind to on the client machine
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static GraphiteClient defaultGraphiteClient(String graphiteHost, int carbonPort, String sourceIp) throws UnknownHostException, SocketException{
		CarbonSender carbonSender = CarbonFactory.getDefaultCarbonSender(graphiteHost, carbonPort, sourceIp);
		return new DefaultGraphiteClient(carbonSender);
	}
	

	/**
	 * Get a QueryableGraphiteClient implementation that connects to the specified host on port 2003
	 * @param graphiteHost
	 * The graphite host to connect to
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static QueryableGraphiteClient queryableGraphiteClient(String graphiteHost, GraphiteWebConfiguration config) throws UnknownHostException, SocketException{
		CarbonSender carbonSender = CarbonFactory.getDefaultCarbonSender(graphiteHost, 2003, "0.0.0.0");
		return new QueryableGraphiteClientImpl(carbonSender, config);
	}

	/**
	 * Get a QueryableGraphiteClient implementation that connects to the specified host on port 2003
	 * @param graphiteHost
	 * The graphite host to connect to
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static QueryableGraphiteClient queryableGraphiteClient(String graphiteHost) throws UnknownHostException, SocketException{
		CarbonSender carbonSender = CarbonFactory.getDefaultCarbonSender(graphiteHost, 2003, "0.0.0.0");
		GraphiteWebConfiguration webConfig = new GraphiteWebConfigurationBuilder()
				.setWebHost(graphiteHost)
				.build();
		return new QueryableGraphiteClientImpl(carbonSender, webConfig);
	}
	
	/**
	 * Get a QueryableGraphiteClient implementation
	 * @param graphiteHost
	 * The graphite host to connect to
	 * @param carbonPort
	 * The port on the graphite host to connect to
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static QueryableGraphiteClient queryableGraphiteClient(String graphiteHost, int carbonPort) throws UnknownHostException, SocketException{
		CarbonSender carbonSender = CarbonFactory.getDefaultCarbonSender(graphiteHost, carbonPort, "0.0.0.0");
		GraphiteWebConfiguration webConfig = new GraphiteWebConfigurationBuilder()
				.setWebHost(graphiteHost)
				.build();
		return new QueryableGraphiteClientImpl(carbonSender, webConfig);
	}
	
	/**
	 * Get a QueryableGraphiteClient implementation
	 * @param graphiteHost
	 * The graphite host to connect to
	 * @param carbonPort
	 * The port on the graphite host to connect to
	 * @param sourceIp
	 * The ip to bind to on the client machine
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static QueryableGraphiteClient queryableGraphiteClient(String graphiteHost, int carbonPort, String sourceIp) throws UnknownHostException, SocketException{
		CarbonSender carbonSender = CarbonFactory.getDefaultCarbonSender(graphiteHost, carbonPort, sourceIp);
		GraphiteWebConfiguration webConfig = new GraphiteWebConfigurationBuilder()
				.setWebHost(graphiteHost)
				.build();
		return new QueryableGraphiteClientImpl(carbonSender, webConfig);
	}
}
