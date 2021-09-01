package com.t9.bsshop.model;

public class CartItem {
	private Plant plant;
	private int quantity;
	
	public CartItem(Plant plant, int quantity) {
		this.plant = plant;
		this.quantity = quantity;
	}
	
	public Plant getPlant() {
		return plant;
	}
	
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void increase(){
		quantity++;
	}
	public void decrease(){
		quantity--;
	}
	public long getSum(){
		return quantity*plant.getPrice();
	}
}
