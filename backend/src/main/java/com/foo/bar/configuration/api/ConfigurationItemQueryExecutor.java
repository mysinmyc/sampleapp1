package com.foo.bar.configuration.api;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foo.bar.configuration.ConfigurationItemQuery;


public interface ConfigurationItemQueryExecutor<T> {

	
	boolean isAvailableFor(ConfigurationItemQuery query);
	
	List<T> execute(ConfigurationItemQuery query);
}
