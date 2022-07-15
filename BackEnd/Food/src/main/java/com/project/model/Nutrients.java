package com.project.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "nutrients")
public class Nutrients {
	
	@Id
	private Integer id;
	private String name;
	private String unitName;
	private String amount;
	private String description;
	
	public Nutrients() {}

	public Nutrients(Integer id, String name, String unitName, String amount, String description) {
		super();
		this.id = id;
		this.name = name;
		this.unitName = unitName;
		this.amount = amount;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
