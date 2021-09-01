package com.t9.bsshop.model;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetails extends BaseEntity{
	@Column private long price;
	@Column private int quantity;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "plant_id")
	private Plant plant;
	
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
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Plant getPlant() {
		return plant;
	}
	
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	public long getSum(){
		return price*quantity;
	}
	public static OrderDetails create(Order order,CartItem item){
		OrderDetails details=new OrderDetails();
		details.price=item.getPlant().getPrice();
		details.quantity=item.getQuantity();
		details.order=order;
		details.plant=item.getPlant();
		return details;
	}
}
