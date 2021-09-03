package com.t9.bsshop.service;


import com.t9.bsshop.model.Category;
import com.t9.bsshop.model.Plant;
import com.t9.bsshop.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo pr;

    public List<Plant> getAll(){
        return this.pr.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }
    public Plant getById(long id){
        return this.pr.getById(id);
    }
    public List<Plant> getAllBy(String name,String cat, String price){
        List<Plant> all = this.pr.findAll();

        if(!"".equalsIgnoreCase(name)){
            Iterator<Plant> i = all.iterator();
            while (i.hasNext()){
                if(!i.next().getName().contains(name)){
                    i.remove();
                }
            }
        }
        if(!"".equalsIgnoreCase(cat)){
            long cid = Integer.parseInt(cat);
            Iterator<Plant> i = all.iterator();
            while (i.hasNext()){
                if(i.next().getCategory().getId() !=cid){
                    i.remove();
                }
            }
        }

        if(!"".equalsIgnoreCase(price)){
            long p = Integer.parseInt(price);
            Iterator<Plant> i = all.iterator();
            while (i.hasNext()){
                if(i.next().getQuantity()>p) {
                    i.remove();
                }
            }
        }
        return all;
    }
    public void addplant(String name, String detail, Category id, String dep, String slug, String qty, String price, String file){
        Plant tmp = new Plant();
        tmp.setName(name);
        tmp.setDetails(detail);
        tmp.setDescription(dep);
        tmp.setSlug(slug);
        tmp.setQuantity(Integer.parseInt(qty));
        tmp.setPrice(Integer.parseInt(price));
        tmp.setCategory(id);
        tmp.setThumbnail(file);
        pr.save(tmp);
    }
    public void editplant(long id,String name, String detail, Category idc, String dep, String slug, String qty, String price, String file){
        Plant tmp = pr.getOne(id);
        tmp.setName(name);
        tmp.setDetails(detail);
        tmp.setDescription(dep);
        tmp.setSlug(slug);
        tmp.setQuantity(Integer.parseInt(qty));
        tmp.setPrice(Integer.parseInt(price));
        tmp.setCategory(idc);
        tmp.setThumbnail(file);
        pr.save(tmp);
    }
    public boolean delPlant(long id){
        try {
            pr.deleteById(id);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
