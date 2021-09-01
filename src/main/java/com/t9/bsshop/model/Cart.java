package com.t9.bsshop.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Cart {
	private Map<Long,CartItem> items=new HashMap<>();
	public Collection<CartItem> getItemsInCart(){
		return items.values();
	}
	public boolean isEmptyCart(){
		return items.isEmpty();
	}
	public void addToCart(Plant plant){
		if(items.containsKey(plant.getId())){
			items.get(plant.getId()).increase();
		} else {
			items.put(plant.getId(),new CartItem(plant,1));
		}
	}
	public void removeFromCart(long plantId) throws NoSuchElementException{
		if(items.containsKey(plantId)){
			CartItem item=items.get(plantId);
			item.decrease();
			if(item.getQuantity()==0){
				items.remove(plantId);
			}
		} else {
			throw new NoSuchElementException();
		}
	}

	public void removeAll(long plantId){
		if(items.containsKey(plantId)){
			items.remove(plantId);
		} else {
			throw new NoSuchElementException();
		}
	}
	public long getCartTotal(){
		return items.values().stream().mapToLong(CartItem::getSum).sum();
	}
}
