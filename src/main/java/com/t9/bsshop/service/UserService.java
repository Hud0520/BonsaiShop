package com.t9.bsshop.service;

import com.t9.bsshop.repository.AccountRepo;
import com.t9.bsshop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends DefaultOAuth2UserService implements UserDetailsService {
	@Autowired private AccountRepo accRepo;
	@Autowired private PasswordEncoder encoder;
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
		account.setPassword(encoder.encode(account.getPassword()));
		accRepo.save(account);
	}
	public Account getUserById(long id){
		return accRepo.getById(id);
	}
	public void updateUserById(long id,Account user){
		Account update=getUserById(id);
		update.setFullName(user.getFullName());
		update.setAddress(user.getAddress());
		update.setTel(user.getTel());
		accRepo.save(update);
	}
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User user=super.loadUser(userRequest);
		SocialAccount socialUser;
		if(userRequest.getClientRegistration().getClientName().equalsIgnoreCase("facebook")){
			socialUser=new FacebookUser(user);
		} else {
			socialUser=new GoogleUser(user);
		}
		Account acc=getUserByEmail(socialUser.getEmail());
		if(acc==null){
			Account newUser=new Account();
			newUser.setFullName(socialUser.getSocialUsername());
			newUser.setEmail(socialUser.getEmail());
			newUser.setAvatar(socialUser.getAvatar());
			createAccount(newUser);
			return UserPrincipal.create(newUser,socialUser.getAttributes());
		}
		return UserPrincipal.create(acc);
	}
	public List<Order> getUserOrders(long userId){
		return getUserById(userId).getOrders();
	}
}
