package com.foo.bar.configuration;

public class ConfigurationItemQuery {

	
	ConfigurationItemClass configurationItemClass;
	
	
	String criteria;



	public ConfigurationItemQuery(ConfigurationItemClass configurationItemClass, String criteria) {
		this.configurationItemClass = configurationItemClass;
		this.criteria = criteria;
	}
	
	public ConfigurationItemClass getConfigurationItemClass() {
		return configurationItemClass;
	}



	public String getCriteria() {
		return criteria;
	}





}
