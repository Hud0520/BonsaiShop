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
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;

@EnableWebSecurity
public class SecurityConfig {
	@Bean
	RequestRejectedHandler requestRejectedHandler() {
		return new HttpStatusRequestRejectedHandler();
	}
	@Configuration
	@Order(1)
	public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
					.passwordEncoder(passwordEncoder())
					.withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN");
		}
		
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/adv/**").authorizeRequests()
					.antMatchers("/adv/login")
					.permitAll()
					.antMatchers("/adv/**")
					.hasAnyRole("ADMIN")
					.and()
					.formLogin()
					.loginPage("/adv/login")
					.defaultSuccessUrl("/adv/")
					.failureUrl("/adv/login?error=true")
					.permitAll()
					.and()
					.logout()
					.logoutSuccessUrl("/adv/login?logout=true")
					.invalidateHttpSession(true)
					.permitAll()
					.and()
					.csrf()
					.disable();
		}
	}
	@Configuration
	public static class UserSecurityConfig extends WebSecurityConfigurerAdapter{
		@Autowired private UserService userService;
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userService)
					.passwordEncoder(passwordEncoder());
		}
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/assets/**");
		}
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			http.authorizeRequests().antMatchers("/login","/register","/","/cart/**","/blog/**","/contact","/categories/**",
					"/products/**","/register","/search","/intro")
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
	@Bean
	public static PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
}
