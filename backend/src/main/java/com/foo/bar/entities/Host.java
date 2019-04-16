package com.foo.bar.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedEntityGraph;

import com.foo.bar.configuration.ConfigurationItemCategory;
import com.foo.bar.configuration.api.ConfigurationItem;
import com.foo.bar.configuration.api.ConfigurationItemField;

@ConfigurationItem(category=ConfigurationItemCategory.Infrastructure,name="Host")
@Entity
@NamedEntityGraph(name = "host.preload", includeAllAttributes=true)
public class Host {

	
	
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

	@ConfigurationItemField
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	@ConfigurationItemField
	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	@ConfigurationItemField
	public void setDescription(String description) {
		this.description = description;
	}

	@ConfigurationItemField
	public ProductiveStage getProductiveStage() {
		return productiveStage;
	}

	public void setProductiveStage(ProductiveStage productiveStage) {
		this.productiveStage = productiveStage;
	}
	
	
}
