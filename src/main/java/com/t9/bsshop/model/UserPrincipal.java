package com.t9.bsshop.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UserPrincipal implements OAuth2User, UserDetails {
	private Account account;
	private Map<String,Object> attributes;
	
	private UserPrincipal(Account account) {
		this.account=account;
	}
	private UserPrincipal(Account account,Map<String,Object> attributes) {
		this(account);
		this.attributes=attributes;
	}
	public static UserPrincipal create(Account account){
		return new UserPrincipal(account);
	}
	public static UserPrincipal create(Account account,Map<String,Object> attributes){
		return new UserPrincipal(account,attributes);
	}
	public long getId() {
		return account.getId();
	}
	
	@Override
	public String getPassword() {
		return account.getPassword();
	}
	
	@Override
	public String getUsername() {
		return account.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities=new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		if(account.isAdmin()){
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
		}
		return authorities;
	}
	
	@Override
	public String getName() {
		return String.valueOf(account.getId());
	}
}
