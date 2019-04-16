package com.foo.bar.configuration;

import java.lang.reflect.Method;

import com.foo.bar.configuration.api.ConfigurationItem;
import com.foo.bar.configuration.api.ConfigurationItemField;

public class ConfigurationItemFieldDescriptor {

	ConfigurationItemField annotation;
	
	ConfigurationItemClassDescriptor parent;
	String name;
	
	public ConfigurationItemFieldDescriptor(String name,ConfigurationItemClassDescriptor parent) {
		this.name=name;
		this.parent = parent;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	Method readMethod;
	
	public ConfigurationItemClassDescriptor getParent() {
		return parent;
	}

	public void setParent(ConfigurationItemClassDescriptor parent) {
		this.parent = parent;
	}

	public Method getReadMethod() {
		return readMethod;
	}

	public void setReadMethod(Method readMethod) {
		
		initMetadata(readMethod);
		this.readMethod = readMethod;
	}

	public Method getWriteMethod() {
		return WriteMethod;
	}

	public void setWriteMethod(Method writeMethod) {
		initMetadata(writeMethod);
		WriteMethod = writeMethod;
	}


	private void initMetadata(Method method) {
		// TODO Auto-generated method stub
		ConfigurationItemField annotation = method.getAnnotation(ConfigurationItemField.class);
		
		if (annotation!=null) {
			this.annotation = annotation;
		}
	}


	Method WriteMethod;
	
	
	public boolean isReadeable() {
		return readMethod!=null;
	}
	
	
	public boolean isConfigurationItemRelation() {
		if (readMethod==null) { 
			return false;
		}
		
		return readMethod.getReturnType().getAnnotation(ConfigurationItem.class) != null;
		
	}


	public String getLabel() {
		return name;
	}
	
	public boolean isVisible() {
		return annotation!=null;
	}
}