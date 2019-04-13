package com.foo.bar.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foo.bar.configuration.api.ConfigurationItemQueryExecutor;


@Component
public class ConfigurationItemDao {

	@Autowired
	List<ConfigurationItemQueryExecutor> queryExecutors; 
	
	public <T> List<T> executeSearch(ConfigurationItemQuery query) {

		
		
		for (ConfigurationItemQueryExecutor currentQueryExecutor : queryExecutors) {
		
			if (currentQueryExecutor.isAvailableFor(query)) {
				
				return currentQueryExecutor.execute(query);
			}
		}
		
		throw new RuntimeException("unsupported query");
	}
}
