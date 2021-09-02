package com.t9.bsshop.controller.customer;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	private boolean isAuthenticated(){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		return auth!=null&&auth.isAuthenticated()&&!(auth instanceof AnonymousAuthenticationToken);
	}
	@GetMapping("/login")
	public String login(){
		if(isAuthenticated()){
			return "redirect:/";
		}
		return "customer/login";
	}
}
