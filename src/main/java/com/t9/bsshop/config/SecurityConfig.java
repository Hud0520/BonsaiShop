package com.t9.bsshop.config;

import com.t9.bsshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration("UserLogin")
@Order(2)
class SecurityConfig1 extends WebSecurityConfigurerAdapter {
	@Autowired private UserService userService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
				.passwordEncoder(passwordEncoder());
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/js/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login","/register","/cart/**")
				.permitAll()
				.anyRequest().authenticated();
		http.formLogin().loginPage("/login").usernameParameter("email")
				.passwordParameter("password").loginProcessingUrl("/do-login")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error")
				.and().oauth2Login().loginPage("/login")
				.userInfoEndpoint().userService(userService)
				.and().defaultSuccessUrl("/")
				.failureUrl("/login?oauth2_error");

	}
}
