package com.gl.capstone.ShopForHomeMain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "catogeries")
public class Catogery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long catogeryId;
	private String catogeryName;
	private String description;
	
	public long getCatogeryId() {
		return catogeryId;
	}

	public void setCatogeryId(long catogeryId) {
		this.catogeryId = catogeryId;
	}

	public String getCatogeryName() {
		return catogeryName;
	}

	public void setCatogeryName(String catogeryName) {
		this.catogeryName = catogeryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}