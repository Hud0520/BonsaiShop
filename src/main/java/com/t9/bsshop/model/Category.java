package com.t9.bsshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "category")
public class Category extends BaseEntity{
//	catName varchar,
//	description varchar,
//	slug varchar
	@Column private String catName;
	@Column private String description;
	@Column private String slug;
	@OneToMany(mappedBy = "category")
	private List<Plant> plants;
	
	public List<Plant> getPlants() {
		return plants;
	}
	
	public void setPlants(List<Plant> plants) {
		this.plants = plants;
	}
	
	public String getCatName() {
		return catName;
	}
	
	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSlug() {
		return slug;
	}
	
	public void setSlug(String slug) {
		this.slug = slug;
	}
}
