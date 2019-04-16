package com.foo.bar.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.foo.bar.configuration.ConfigurationItemCategory;
import com.foo.bar.configuration.api.ConfigurationItem;
import com.foo.bar.configuration.api.ConfigurationItemField;
import com.foo.bar.configuration.api.Searchable;

@ConfigurationItem(category=ConfigurationItemCategory.Business,name="Service")
@Entity
public class BusinessService {

	

	
	
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	BusinessArea businessArea;

	@ConfigurationItemField
	public BusinessArea getBusinessArea() {
		return businessArea;
	}


	public void setBusinessArea(BusinessArea businessArea) {
		this.businessArea = businessArea;
	}
}
