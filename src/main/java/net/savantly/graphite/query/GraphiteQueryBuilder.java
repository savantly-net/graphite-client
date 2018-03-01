package net.savantly.graphite.query;

import com.fasterxml.jackson.databind.JsonNode;

import net.savantly.graphite.query.fomat.CsvFormatter;
import net.savantly.graphite.query.fomat.JsonFormatter;
import net.savantly.graphite.query.impl.FromImpl;
import net.savantly.graphite.query.impl.TargetImpl;
import net.savantly.graphite.query.impl.UntilImpl;

public class GraphiteQueryBuilder<T> {
	
	private Target target;
	private From from = new FromImpl();
	private Until until = new UntilImpl();
	private final Formatter<T> format;
	private Template template;

	public GraphiteQueryBuilder(Formatter<T> format) {
		this.format = format;
	}
	
	static public GraphiteQuery<JsonNode> simpleQuery(String target) {
		return new GraphiteQueryBuilder<JsonNode>(new JsonFormatter())
				.setTarget(target)
				.build();
	}
	
	static public GraphiteQuery<String> csvQuery(String target) {
		return new GraphiteQueryBuilder<String>(new CsvFormatter())
				.setTarget(target)
				.build();
	}

	public Target getTarget() {
		return target;
	}

	public GraphiteQueryBuilder<T> setTarget(Target target) {
		this.target = target;
		return this;
	}
	public GraphiteQueryBuilder<T> setTarget(String target) {
		this.target = new TargetImpl(target);
		return this;
	}

	// FROM ************************
	public From getFrom() {
		return from;
	}
	public GraphiteQueryBuilder<T> setFrom(From from) {
		this.from = from;
		return this;
	}
	public GraphiteQueryBuilder<T> setFrom(String from) {
		this.from = new FromImpl(from);
		return this;
	}
	public GraphiteQueryBuilder<T> setFrom(int value, GraphiteTimeUnit unit) {
		this.from = new FromImpl(value, unit);
		return this;
	}

	// UNTIL ************************
	public Until getUntil() {
		return until;
	}
	public GraphiteQueryBuilder<T> setUntil(Until until) {
		this.until = until;
		return this;
	}
	public GraphiteQueryBuilder<T> setUntil(String until) {
		this.until = new UntilImpl(until);
		return this;
	}
	public GraphiteQueryBuilder<T> setUntil(int value, GraphiteTimeUnit unit) {
		this.until = new UntilImpl(value, unit);
		return this;
	}
	

	public Formatter<T> getFormat() {
		return format;
	}

	public Template getTemplate() {
		return template;
	}

	public GraphiteQueryBuilder<T> setTemplate(Template template) {
		this.template = template;
		return this;
	}
	

	public GraphiteQuery<T> build() {
		if(target == null) {
			throw new AssertionError("target must not be null");
		}
		return new GraphiteQuery<T>() {

			@Override
			public Target target() {
				return target;
			}

			@Override
			public From from() {
				return from;
			}

			@Override
			public Until until() {
				return until;
			}

			@Override
			public Formatter<T> format() {
				return format;
			}

			@Override
			public Template template() {
				return template;
			}
		};
	}

}
