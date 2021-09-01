package com.t9.bsshop.service;

import com.t9.bsshop.model.Cart;
import com.t9.bsshop.model.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;

@Service
public class CartService {
	@Autowired
	private PlantService plantService;
	
	public Cart getCart(HttpSession session) {
		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart_count", 0);
			session.setAttribute("cart", new Cart());
		}
		return (Cart) session.getAttribute("cart");
	}
	
	public void addToCart(HttpSession session, long plantId) {
		Plant plant = plantService.getPlantById(plantId);
		if (plant == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		Cart cart = getCart(session);
		cart.addToCart(plant);
		session.setAttribute("cart_count",cart.getItemsInCart().size());
	}
	
	public void removeFormCart(HttpSession session, long plantId) {
		try {
			Cart cart= getCart(session);
			cart.removeFromCart(plantId);
			session.setAttribute("cart_count",cart.getItemsInCart().size());
		} catch (NoSuchElementException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	public void removeAll(HttpSession session,long plantId){
		try {
			Cart cart= getCart(session);
			cart.removeAll(plantId);
			session.setAttribute("cart_count",cart.getItemsInCart().size());
		} catch (NoSuchElementException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	public void destroyCart(HttpSession session) {
		session.removeAttribute("cart");
		session.removeAttribute("cart_count");
	}
}
