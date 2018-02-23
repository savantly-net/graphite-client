package net.savantly.graphite.web;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphiteWebConfigurationBuilder {
	private static final Logger log = LoggerFactory.getLogger(GraphiteWebConfigurationBuilder.class);
	private static final String DEFAULT_SCHEME = "http";
	private static final String DEFAULT_WEB_HOST = "localhost";
	private static final int DEFAULT_WEB_PORT = 8080;
	private static final String DEFAULT_RENDER_PATH = "/render";
	
	private String scheme = DEFAULT_SCHEME;
	private String webHost = DEFAULT_WEB_HOST;
	private int webPort = DEFAULT_WEB_PORT;
	private String renderPath = DEFAULT_RENDER_PATH;
	private String userInfo = null;

	public GraphiteWebConfiguration build() {
		return new GraphiteWebConfiguration() {

			@Override
			public URI getRenderUrl() {
				try {
					URI uri = new URI(scheme, userInfo, webHost, webPort, renderPath, null, null);
					return uri;
				} catch (URISyntaxException e) {
					log.error(e.getMessage());
					throw new RuntimeException(e);
				}
			}
		};
	}

	public String getScheme() {
		return scheme;
	}

	public GraphiteWebConfigurationBuilder setScheme(String scheme) {
		this.scheme = scheme;
		return this;
	}

	public String getWebHost() {
		return webHost;
	}

	public GraphiteWebConfigurationBuilder setWebHost(String webHost) {
		this.webHost = webHost;
		return this;
	}

	public int getWebPort() {
		return webPort;
	}

	public GraphiteWebConfigurationBuilder setWebPort(int webPort) {
		this.webPort = webPort;
		return this;
	}

	public String getRenderPath() {
		return renderPath;
	}

	public GraphiteWebConfigurationBuilder setRenderPath(String renderPath) {
		this.renderPath = renderPath;
		return this;
	}

}
