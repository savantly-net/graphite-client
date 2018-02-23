package net.savantly.graphite.query;

public interface GraphiteQuery<T> {

	Target target();
	From from();
	Until until();
	Formatter<T> format();
	Template template();
}
