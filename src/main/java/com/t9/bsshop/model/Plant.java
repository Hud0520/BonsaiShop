package com.t9.bsshop.model;

import javax.persistence.*;

@Entity
@Table(name = "plant")
public class Plant extends BaseEntity{
//	catId bigint references category(id),
//	name varchar,
//	description varchar,
//	quantity int,
//	price bigint,
//	details text,
//	thumbnail varchar,
//	slug varchar
	@Column private String name;
	@Column private String description;
	@Column private String details;
	@Column private String thumbnail;
	@Column private String slug;
	@Column private long price;
	@Column private int quantity;
	@ManyToOne
	@JoinColumn(name = "cat_id")
	private Category category;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public String getSlug() {
		return slug;
	}
	
	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	public long getPrice() {
		return price;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
}
