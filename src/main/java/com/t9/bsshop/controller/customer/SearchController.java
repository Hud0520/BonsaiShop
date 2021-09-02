package com.t9.bsshop.controller.customer;

import com.t9.bsshop.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired private PlantService plantService;
	@GetMapping
	public String getSearchResults(@RequestParam String q, Model model){
		model.addAttribute("plants",plantService.search(q));
		return "customer/search";
	}
}