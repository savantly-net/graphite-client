package net.savantly.graphite.query;

import java.net.URLConnection;

public interface Formatter<T> extends HasStringValue {

	T getData(URLConnection connection);
}
