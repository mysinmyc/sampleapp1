package com.foo.bar.configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationItemClassDescriptor {

	public ConfigurationItemClassDescriptor() {

	}

	public ConfigurationItemClassDescriptor(Class javaClass, ConfigurationItemCategory category, String name) {
		this.javaClass = javaClass;
		this.name = name;
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

	Map<String, ConfigurationItemFieldDescriptor> fields;

	public Map<String, ConfigurationItemFieldDescriptor> getFields() {

		if (fields == null) {
			initFields();
		}

		return fields;
	}

	private void initFields() {

		Map<String, ConfigurationItemFieldDescriptor> fields = new HashMap<String, ConfigurationItemFieldDescriptor>();
		for (Method currentMethod : javaClass.getMethods()) {

			if (currentMethod.getName().startsWith("get") && currentMethod.getParameterCount() == 0) {

				String currentFieldName = currentMethod.getName().substring(3).toLowerCase();

				ConfigurationItemFieldDescriptor currentDescriptor = fields.get(currentFieldName);

				if (currentDescriptor == null) {
					currentDescriptor = new ConfigurationItemFieldDescriptor(currentFieldName, this);
					fields.put(currentFieldName, currentDescriptor);
				}
				currentDescriptor.setReadMethod(currentMethod);
				continue;
			}
			

			if (currentMethod.getName().startsWith("set") && currentMethod.getParameterCount() == 1) {

				String currentFieldName = currentMethod.getName().substring(3).toLowerCase();

				ConfigurationItemFieldDescriptor currentDescriptor = fields.get(currentFieldName);

				if (currentDescriptor == null) {
					currentDescriptor = new ConfigurationItemFieldDescriptor(currentFieldName, this);
					fields.put(currentFieldName, currentDescriptor);
				}
				currentDescriptor.setWriteMethod(currentMethod);
				continue;
			}
		}
		this.fields = fields;
	}
}
