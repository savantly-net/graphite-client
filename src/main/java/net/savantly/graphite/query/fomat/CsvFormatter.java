package net.savantly.graphite.query.fomat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.savantly.graphite.query.Formatter;

public class CsvFormatter implements Formatter<String> {
	private final static Logger log = LoggerFactory.getLogger(CsvFormatter.class);

	@Override
	public String value() {
		return "csv";
	}

	@Override
	public String getData(URLConnection connection) {
		try {
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();
            return sb.toString();
		} catch (IOException e) {
			log.error("failed to get data");
			throw new RuntimeException(e);
		}
	}

}
