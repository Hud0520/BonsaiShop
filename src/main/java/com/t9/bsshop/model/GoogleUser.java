package com.t9.bsshop.model;

import org.springframework.security.oauth2.core.user.OAuth2User;

public class GoogleUser extends SocialAccount{
	
	public GoogleUser(OAuth2User user) {
		super(user.getAuthorities(),user.getAttributes(),"sub");
	}
	
	@Override
	public String getEmail() {
		return getAttribute("email");
	}
	
	@Override
	public String getSocialUsername() {
		return getAttribute("name");
	}
	
	@Override
	public String getAvatar() {
		return getAttribute("picture");
	}
}
