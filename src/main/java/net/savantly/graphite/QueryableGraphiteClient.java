package net.savantly.graphite;

import net.savantly.graphite.query.GraphiteQuery;

public interface QueryableGraphiteClient extends GraphiteClient {

	<T> T query (GraphiteQuery<T> query);
}
