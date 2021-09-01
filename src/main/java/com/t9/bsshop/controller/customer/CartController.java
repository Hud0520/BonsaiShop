package com.t9.bsshop.controller.customer;

import com.t9.bsshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired private CartService cartService;
	@GetMapping
	public String getCart(HttpSession session, Model model){
		model.addAttribute("cart",cartService.getCart(session));
		return "customer/cart";
	}
	@GetMapping("/add")
	public String addToCart(HttpSession session, @RequestParam("id") long plantId){
		cartService.addToCart(session,plantId);
		return "redirect:/cart";
	}
	@GetMapping("/remove")
	public String removeFromCart(HttpSession session, @RequestParam("id") long plantId){
		cartService.removeFormCart(session,plantId);
		return "redirect:/cart";
	}
	@GetMapping("/remove_all")
	public String removeAll(HttpSession session,@RequestParam("id") long plantId){
		cartService.removeAll(session,plantId);
		return "redirect:/cart";
	}
}
