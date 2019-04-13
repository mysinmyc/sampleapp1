package com.foo.bar.configuration;

public class ConfigurationItemClass{

	
	public ConfigurationItemClass() {
		
	}
	
	public ConfigurationItemClass(Class javaClass, String name) {
		this.javaClass = javaClass;
		this.name=name;
	}
	
	public void setJavaClass(Class javaClass) {
		this.javaClass = javaClass;
	}

	public void setName(String name) {
		this.name = name;
	}

	Class javaClass;
	
	String name;
	
	public String getName() {
		return name;
	}

	public Class getJavaClass() {
		return javaClass;
	}

	
}
