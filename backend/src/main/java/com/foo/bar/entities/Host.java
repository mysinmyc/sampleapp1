package com.foo.bar.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.foo.bar.configuration.api.ConfigurationItem;

@ConfigurationItem(name="Infrastructure - Host")
@Entity
public class Host {

	
	
	@Id
	@GeneratedValue
	Long id;
	
	
	String name;
	
	
	String address;

	
	String description;
	
	@ManyToOne
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
}
