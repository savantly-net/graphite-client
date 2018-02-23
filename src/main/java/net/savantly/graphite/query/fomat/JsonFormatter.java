package net.savantly.graphite.query.fomat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.savantly.graphite.query.Formatter;

public class JsonFormatter implements Formatter<JsonNode>{
	private final static Logger log = LoggerFactory.getLogger(JsonFormatter.class);

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String value() {
		return "json";
	}

	@Override
	public JsonNode getData(URLConnection connection) {
		try {
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();
            String jsonString = sb.toString();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return jsonNode;
		} catch (IOException e) {
			log.error("failed to get data");
			throw new RuntimeException(e);
		}
	}
}
