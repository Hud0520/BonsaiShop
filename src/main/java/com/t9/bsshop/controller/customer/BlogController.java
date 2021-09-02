package com.t9.bsshop.controller.customer;

import com.t9.bsshop.model.Blog;
import com.t9.bsshop.service.BlogService;
import com.t9.bsshop.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller("UserBlog")
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
