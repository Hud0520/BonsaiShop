package com.t9.bsshop.service;

import com.t9.bsshop.model.Order;
import com.t9.bsshop.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class OrderService {
	@Autowired private OrderRepo orderRepo;
	public void createOrder(Order order){
		orderRepo.save(order);
	}
	public Order getSessionOrder(HttpSession session){
		if(session.getAttribute("order")==null){
			session.setAttribute("order",new Order());
		}
		return (Order) session.getAttribute("order");
	}
	public int getOrderStep(HttpSession session){
		if(session.getAttribute("order_step")==null){
			session.setAttribute("order_step",1);
		}
		return (int) session.getAttribute("order_step");
	}
	public void setOrderStep(HttpSession session,int step){
		session.setAttribute("order_step",step);
	}
	public void destroyOrder(HttpSession session){
		session.removeAttribute("order");
		session.removeAttribute("order_step");
	}
}
