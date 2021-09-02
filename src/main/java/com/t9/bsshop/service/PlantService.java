package com.t9.bsshop.service;

import com.t9.bsshop.model.Category;
import com.t9.bsshop.model.Plant;
import com.t9.bsshop.repository.PlantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {
	@Autowired private PlantRepo plantRepo;
	public Plant getPlantById(long id){
		return plantRepo.findById(id).orElse(null);
	}
	public List<Plant> getTopSellingPlant(){
		return plantRepo.getTopSellingPlants(Pageable.ofSize(20));
	}
	public Page<Plant> getPlantsInCat(Category cat, int page){
		return plantRepo.findAllByCategoryEquals(cat, PageRequest.of(page,12));
	}
	public List<Plant> search(String q){
		return plantRepo.search("%"+q+"%");
	}
}
