package com.foo.bar.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foo.bar.configuration.ConfigurationItemQuery;
import com.foo.bar.configuration.api.ConfigurationItemQueryExecutor;
import com.foo.bar.entities.Host;

@Component
public class HostQueryExecutor implements ConfigurationItemQueryExecutor<Host>{

	@Autowired
	HostRepository hostRepository;



	@Override
	public boolean isAvailableFor(ConfigurationItemQuery query) {
		return Host.class.isAssignableFrom(query.getConfigurationItemClass().getJavaClass());
	}

	@Override
	public List<Host> execute(ConfigurationItemQuery query) {
		return hostRepository.genericSearch(query.getCriteria());
	}

}
