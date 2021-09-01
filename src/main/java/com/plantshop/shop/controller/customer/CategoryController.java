package com.plantshop.shop.controller.customer;

import com.plantshop.shop.model.Category;
import com.plantshop.shop.model.Plant;
import com.plantshop.shop.service.CategoryService;
import com.plantshop.shop.service.PlantService;
import com.plantshop.shop.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	@Autowired private CategoryService catService;
	@Autowired private PlantService plantService;
	@GetMapping("/{id}")
	public String getCategory(@PathVariable long id,@RequestParam(name = "page",defaultValue = "1") int page, Model model){
		Category cat=catService.getCategoryById(id);
		model.addAttribute("cat",cat);
		Page<Plant> pagedPlants=plantService.getPlantsInCat(cat,page-1);
		model.addAttribute("plants",pagedPlants.toList());
		if(pagedPlants.getTotalPages()>1){
			model.addAttribute("pages",new Pagination(page,pagedPlants.getTotalPages()).getPages());
		}
		return "customer/category";
	}
}
