package com.t9.bsshop.service;

import com.t9.bsshop.model.Category;
import com.t9.bsshop.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
	@Autowired private CategoryRepo catRepo;
	@Cacheable("categories")
	public List<Category> getCategories(){
		return catRepo.findAll();
	}

	@Autowired
	private CategoryRepo cr;

	public List<Category> getAll(){
		return this.cr.findAll(Sort.by(Sort.Direction.DESC,"id"));
	}
	public Category getById(long id){
		return this.cr.getById(id);
	}
	public List<Category> getAllBy(String keyword){
		List<Category> all = this.getAll();
		List<Category> list = new ArrayList<>();
		for(Category item : all){
			if(item.getCatName().contains(keyword)){
				list.add(item);
			}
		}
		return list;
	}
	public void addCat(String name, String dep, String slug){
		Category tmp = new Category();
		tmp.setCatName(name);
		tmp.setDescription(dep);
		tmp.setSlug(slug);
		cr.save(tmp);
	}
	public void editCat(long id, String name, String dep, String slug){
		Category tmp = cr.getOne(id);
		tmp.setCatName(name);
		tmp.setDescription(dep);
		tmp.setSlug(slug);
		cr.save(tmp);
	}
	public boolean delCat(long id){

		try {
			cr.deleteById(id);
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	public Category getCategoryById(long id){
		return catRepo.findById(id).orElse(null);
	}
}
