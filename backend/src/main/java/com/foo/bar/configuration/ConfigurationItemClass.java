package com.foo.bar.configuration;

public class ConfigurationItemClass{

	
	public ConfigurationItemClass() {
		
	}
	
	public ConfigurationItemClass(Class javaClass, ConfigurationItemCategory category, String name) {
		this.javaClass = javaClass;
		this.name=name;
	}
	

	public void setJavaClass(Class javaClass) {
		this.javaClass = javaClass;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(ConfigurationItemCategory category) {
		this.category = category;
	}


	Class javaClass;
	
	String name;
	
	public String getName() {
		return name;
	}

	public Class getJavaClass() {
		return javaClass;
	}

	
	ConfigurationItemCategory category;

	public ConfigurationItemCategory getCategory() {
		return category;
	}
	
	public String getLabel() {
		return category.name() + " - " + name;
	}
}
