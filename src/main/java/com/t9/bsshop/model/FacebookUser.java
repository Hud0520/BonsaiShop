package com.t9.bsshop.model;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class FacebookUser extends SocialAccount{
	public FacebookUser(OAuth2User user) {
		super(user.getAuthorities(),user.getAttributes(),"id");
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
		Map<String,Object> picture=getAttribute("picture");
		Map<String,Object> data= (Map<String, Object>) picture.get("data");
		return data.get("url").toString();
	}
	
}
