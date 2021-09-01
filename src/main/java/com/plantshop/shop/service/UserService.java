package com.plantshop.shop.service;

import com.plantshop.shop.model.Account;
import com.plantshop.shop.model.UserPrincipal;
import com.plantshop.shop.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends DefaultOAuth2UserService implements UserDetailsService {
	@Autowired private AccountRepo accRepo;
	public Account getUserByEmail(String email){
		return accRepo.findByEmail(email).orElse(null);
	}
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Optional<Account> user=accRepo.findByEmail(s);
		if(user.isPresent()){
			return UserPrincipal.create(user.get());
		}
		throw new UsernameNotFoundException("");
	}
	public void createAccount(Account account){
		accRepo.save(account);
	}
}
