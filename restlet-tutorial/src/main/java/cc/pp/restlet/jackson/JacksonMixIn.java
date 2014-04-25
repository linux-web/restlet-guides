package cc.pp.restlet.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.objectify.Key;

public interface JacksonMixIn {

	@JsonIgnore
	<V> Key<V> getRoot();
}
