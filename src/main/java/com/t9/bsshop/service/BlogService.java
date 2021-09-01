package com.t9.bsshop.service;

import com.t9.bsshop.model.Blog;
import com.t9.bsshop.repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
	@Autowired private BlogRepo blogRepo;
	@Cacheable("recentBlogs")
	public List<Blog> getRecentBlogs(){
		return blogRepo.findAllByOrderByCreatedDateAsc(PageRequest.of(0,10));
	}
}
