package com.t9.bsshop.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

public abstract class SocialAccount extends DefaultOAuth2User {
	
	public SocialAccount(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey) {
		super(authorities, attributes, nameAttributeKey);
	}
	
	public abstract String getEmail();
	public abstract String getSocialUsername();
	public abstract String getAvatar();
	
}
