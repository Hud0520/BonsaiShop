package com.plantshop.shop.config;

import com.plantshop.shop.service.BlogService;
import com.plantshop.shop.service.CategoryService;
import com.plantshop.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
