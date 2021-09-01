package com.plantshop.shop.service;

import com.plantshop.shop.model.Category;
import com.plantshop.shop.model.Plant;
import com.plantshop.shop.repository.PlantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {
	@Autowired private PlantRepo plantRepo;
	public Plant getPlantById(long id){
		return plantRepo.findById(id).orElse(null);
	}
	public Page<Plant> getPlantsInCat(Category cat,int page){
		return plantRepo.findAllByCategoryEquals(cat, PageRequest.of(page,12));
	}
	public List<Plant> search(String q){
		return plantRepo.search("%"+q+"%");
	}
}
