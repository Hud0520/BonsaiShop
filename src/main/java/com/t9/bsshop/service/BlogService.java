package com.t9.bsshop.service;

import com.t9.bsshop.model.Blog;
import com.t9.bsshop.repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

@Service
public class BlogService {
	@Autowired private BlogRepo blogRepo;
	@Cacheable("recentBlogs")
	public List<Blog> getRecentBlogs(){
		return blogRepo.findAllByOrderByIdAsc(PageRequest.of(0,10));
	}

	@Autowired
	private BlogRepo br;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public List<Blog> getAll(){
		return this.br.findAll(Sort.by(Sort.Direction.DESC,"id"));
	}
	public Blog getById(long id){
		return this.br.getById(id);
	}
	public List<Blog> getAllBy(String keyword){
		List<Blog> all = br.findAll();
		Iterator<Blog> i = all.iterator();
		while (i.hasNext()){
			if(!i.next().getTitle().contains(keyword)){
				i.remove();
			}
		}
		return all;
	}
	public void addBlog(String name, String content, String slug){
		Blog tmp = new Blog();
		tmp.setTitle(name);
		tmp.setContent(content);
		tmp.setThumbnail(slug);
		tmp.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
		br.save(tmp);
	}
	public void editBlog(long id,String name, String content,  String slug){
		Blog tmp = br.getById(id);
		tmp.setTitle(name);
		tmp.setContent(content);
		tmp.setThumbnail(slug);
		br.save(tmp);
	}
	public void delBlog(long id){
		br.deleteById(id);
	}
	public Blog getBlogById(long id){
		return blogRepo.findById(id).orElse(null);
	}
	public Page<Blog> getPagedBlogs(int page){
		return blogRepo.findAll(PageRequest.of(page,12));
	}
}
