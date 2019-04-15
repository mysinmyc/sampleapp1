package com.foo.bar.ui.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;import org.springframework.web.server.WebFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
	    .antMatchers("/frontend/**").permitAll()
	    .antMatchers("/VAADIN/**").permitAll()
	   	.regexMatchers("\\/[^\\/]*uidl.*").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		//.loginPage("/loginPage.html")
		.loginPage("/loginPage")
		.loginProcessingUrl("/login")
		.permitAll().and().logout()
		.logoutUrl("/logout")
		.permitAll();
	}
	
	@Autowired
	@Value("${APPUSERS_PASSWORD:passw0rd}")
	String appUsersPassword;
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		
		System.out.println("Password is "+appUsersPassword);

		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password(appUsersPassword).roles("ADMIN")
				.build();

		UserDetails user1 = User.withDefaultPasswordEncoder().username("user1").password(appUsersPassword).roles("USER")
				.build();

		UserDetails user2 = User.withDefaultPasswordEncoder().username("user2").password(appUsersPassword).roles("USER")
				.build();

		return new InMemoryUserDetailsManager(admin, user1,user2);
	}

}
