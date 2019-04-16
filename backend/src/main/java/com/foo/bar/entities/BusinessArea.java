package com.foo.bar.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.foo.bar.configuration.ConfigurationItemCategory;
import com.foo.bar.configuration.api.ConfigurationItem;
import com.foo.bar.configuration.api.ConfigurationItemField;
import com.foo.bar.configuration.api.Searchable;

@ConfigurationItem(category=ConfigurationItemCategory.Business,name="Area")
@Entity
public class BusinessArea {

	

	
	
	@Id
	@GeneratedValue
	Long id;
	
	@Searchable
	String name;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@ConfigurationItemField
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
	
		return name;
	}
}
