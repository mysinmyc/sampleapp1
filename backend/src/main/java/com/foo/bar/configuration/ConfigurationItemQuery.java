package com.foo.bar.configuration;

public class ConfigurationItemQuery {

	
	ConfigurationItemClassDescriptor configurationItemClass;
	
	
	String criteria;



	public ConfigurationItemQuery(ConfigurationItemClassDescriptor configurationItemClass, String criteria) {
		this.configurationItemClass = configurationItemClass;
		this.criteria = criteria;
	}
	
	public ConfigurationItemClassDescriptor getConfigurationItemClass() {
		return configurationItemClass;
	}



	public String getCriteria() {
		return criteria;
	}





}
