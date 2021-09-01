package com.plantshop.shop.controller.customer;

import com.plantshop.shop.model.Blog;
import com.plantshop.shop.service.BlogService;
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
@RequestMapping("/blog")
public class BlogController {
	@Autowired private BlogService blogService;
	@GetMapping
	public String getBlogList(Model model, @RequestParam(defaultValue = "1") int page){
		Page<Blog> pagedBlog=blogService.getPagedBlogs(page-1);
		model.addAttribute("blogs",pagedBlog.toList());
		model.addAttribute("pages",new Pagination(page,pagedBlog.getTotalPages()).getPages());
		return "customer/blog";
	}
	@GetMapping("/{id}")
	public String getBlog(Model model, @PathVariable long id){
		model.addAttribute("blog",blogService.getBlogById(id));
		return "customer/blog-details";
	}
}
