package net.savantly.graphite.impl;

import java.io.DataOutputStream;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.savantly.graphite.CarbonSender;
import net.savantly.graphite.QueryableGraphiteClient;
import net.savantly.graphite.query.GraphiteQuery;
import net.savantly.graphite.query.ParameterStringBuilder;
import net.savantly.graphite.web.GraphiteWebConfiguration;
import net.savantly.graphite.web.GraphiteWebConfigurationBuilder;

public class QueryableGraphiteClientImpl extends DefaultGraphiteClient implements QueryableGraphiteClient {
	
	private static final Logger log = LoggerFactory.getLogger(QueryableGraphiteClientImpl.class);
	private GraphiteWebConfiguration webConfig;
	

	public QueryableGraphiteClientImpl(CarbonSender carbonSender) {
		this(carbonSender, new GraphiteWebConfigurationBuilder().build());
	}

	public QueryableGraphiteClientImpl(CarbonSender carbonSender, GraphiteWebConfiguration webConfig) {
		super(carbonSender);
		this.webConfig = webConfig;
	}

	@Override
	public <T> T query(GraphiteQuery<T> query) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("format", query.format().value());
		parameters.put("target", query.target().value());
		parameters.put("from", query.from().value());
		parameters.put("until", query.until().value());
		// TODO: parameters.put("template", query.template().value());
		try {
			URLConnection connection = this.webConfig.getRenderUrl().toURL().openConnection();
			connection.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
			out.flush();
			out.close();
			return query.format().getData(connection);
		} catch (Exception e) {
			log.error("{}", e);
			throw new RuntimeException(e);
		}
	}
}
