package com.plantshop.shop.service;

import com.plantshop.shop.model.Category;
import com.plantshop.shop.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
	@Autowired private CategoryRepo catRepo;
	@Cacheable("categories")
	public List<Category> getCategories(){
		return catRepo.findAll();
	}
	public Category getCategoryById(long id){
		return catRepo.findById(id).orElse(null);
	}
}
