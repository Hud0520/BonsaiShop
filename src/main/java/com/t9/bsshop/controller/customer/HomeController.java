package com.t9.bsshop.controller.customer;

import com.t9.bsshop.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("UserHome")
public class HomeController {
	@Autowired private PlantService plantService;
	@GetMapping("/")
	public String getHome(Model model){
		model.addAttribute("plants",plantService.getTopSellingPlant());
		return "customer/home";
	}
	@GetMapping("/contact")
	public String getContact(){
		return "customer/contact";
	}
	@GetMapping("/intro")
	public String getIntroPage(){
		return "customer/intro";
	}
}
