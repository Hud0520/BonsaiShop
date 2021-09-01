package com.t9.bsshop.config;

import com.t9.bsshop.service.BlogService;
import com.t9.bsshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DataProviderInterceptor implements HandlerInterceptor {
	@Autowired private CategoryService catService;
	@Autowired private BlogService blogService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("categories",catService.getCategories());
		request.setAttribute("recentBlogs",blogService.getRecentBlogs());
		return true;
	}
}
