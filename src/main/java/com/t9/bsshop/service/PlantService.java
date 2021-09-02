package com.t9.bsshop.service;

import com.t9.bsshop.model.Plant;
import com.t9.bsshop.repository.PlantRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
}
