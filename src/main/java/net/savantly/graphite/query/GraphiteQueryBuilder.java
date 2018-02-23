package net.savantly.graphite.query;

import net.savantly.graphite.query.impl.FromImpl;
import net.savantly.graphite.query.impl.TargetImpl;
import net.savantly.graphite.query.impl.UntilImpl;

@SuppressWarnings("rawtypes")
public class GraphiteQueryBuilder<T> {
	
	private Target target;
	private From from = new FromImpl();
	private Until until = new UntilImpl();
	private final Formatter<T> format;
	private Template template;

	public GraphiteQueryBuilder(Formatter<T> format) {
		this.format = format;
	}

	public Target getTarget() {
		return target;
	}

	public GraphiteQueryBuilder setTarget(Target target) {
		this.target = target;
		return this;
	}
	public GraphiteQueryBuilder setTarget(String target) {
		this.target = new TargetImpl(target);
		return this;
	}

	// FROM ************************
	public From getFrom() {
		return from;
	}
	public GraphiteQueryBuilder setFrom(From from) {
		this.from = from;
		return this;
	}
	public GraphiteQueryBuilder setFrom(String from) {
		this.from = new FromImpl(from);
		return this;
	}
	public GraphiteQueryBuilder setFrom(int value, GraphiteTimeUnit unit) {
		this.from = new FromImpl(value, unit);
		return this;
	}

	// UNTIL ************************
	public Until getUntil() {
		return until;
	}
	public GraphiteQueryBuilder setUntil(Until until) {
		this.until = until;
		return this;
	}
	public GraphiteQueryBuilder setUntil(String until) {
		this.until = new UntilImpl(until);
		return this;
	}
	public GraphiteQueryBuilder setUntil(int value, GraphiteTimeUnit unit) {
		this.until = new UntilImpl(value, unit);
		return this;
	}
	

	public Formatter<T> getFormat() {
		return format;
	}

	public Template getTemplate() {
		return template;
	}

	public GraphiteQueryBuilder setTemplate(Template template) {
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
