package com.foo.bar.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.foo.bar.configuration.ConfigurationItemCategory;
import com.foo.bar.configuration.api.ConfigurationItem;

@ConfigurationItem(category=ConfigurationItemCategory.Infrastructure,name="Host group")
@Entity
public class HostGroup {

	
	
	@Id
	@GeneratedValue
	Long id;
	
	
	String name;
	
	
	String address;

	
	String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	ProductiveStage productiveStage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductiveStage getProductiveStage() {
		return productiveStage;
	}

	public void setProductiveStage(ProductiveStage productiveStage) {
		this.productiveStage = productiveStage;
	}
	
	@ManyToMany
	List<BusinessService> services;

	public List<BusinessService> getServices() {
		return services;
	}

	public void setServices(List<BusinessService> services) {
		this.services = services;
	} 
}
