package com.t9.bsshop.controller.customer;

import com.t9.bsshop.model.Plant;
import com.t9.bsshop.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class PlantController {
	@Autowired private PlantService plantService;
	@GetMapping("/{id}")
	public String getPlantDetails(@PathVariable("id") long id, Model model){
		Plant plant=plantService.getPlantById(id);
		model.addAttribute("plant",plant);
		List<Plant> related=plant.getCategory().getPlants()
				.stream().filter(p->p.getId()!=id).limit(6).collect(Collectors.toList());
		model.addAttribute("plants",related);
		return "customer/product-details";
	}
}