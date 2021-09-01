package com.plantshop.shop.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/contact")
	public String getContact(){
		return "customer/contact";
	}
	@GetMapping("/intro")
	public String getIntroPage(){
		return "customer/intro";
	}
}
