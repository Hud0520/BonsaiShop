package com.t9.bsshop.controller.customer;

import com.t9.bsshop.model.Account;
import com.t9.bsshop.model.Order;
import com.t9.bsshop.model.UserPrincipal;
import com.t9.bsshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
	@Autowired private UserService userService;
	@Autowired private PasswordEncoder passwordEncoder;
	@GetMapping("/user-info")
	public String userInfo(@AuthenticationPrincipal UserPrincipal principal, Model model){
		model.addAttribute("user",userService.getUserById(principal.getId()));
		return "customer/account-info";
	}
	@PostMapping("/user-info")
	public String updateUserInfo(@AuthenticationPrincipal UserPrincipal principal,@ModelAttribute Account user){
		userService.updateUserById(principal.getId(),user);
		return "redirect:/user-info";
	}
	@GetMapping("/change-pass")
	public String changePass(@AuthenticationPrincipal UserPrincipal principal,Model model){
		model.addAttribute("user",userService.getUserById(principal.getId()));
		return "customer/change-pass";
	}
	@PostMapping("/change-pass")
	public String doChangePass(@AuthenticationPrincipal UserPrincipal principal, @RequestParam("old-pass")
	                           String old,@RequestParam("new-pass") String newPass){
		Account acc=userService.getUserById(principal.getId());
		if(passwordEncoder.matches(old,principal.getPassword())){
			acc.setPassword(passwordEncoder.encode(newPass));
			userService.updateUserById(principal.getId(),acc);
			return "redirect:/change-pass?success";
		}
		return "redirect:/change-pass?error";
	}
	@GetMapping("/my-orders")
	public String viewMyOrder(@AuthenticationPrincipal UserPrincipal principal,Model model){
		model.addAttribute("user",userService.getUserById(principal.getId()));
		List<Order> orders=userService.getUserOrders(principal.getId());
		model.addAttribute("orders",orders);
		return "customer/user-orders";
	}
}
