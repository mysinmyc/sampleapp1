package com.foo.bar.ui.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/*

				.loginPage("/login.html").loginProcessingUrl("/login.do").defaultSuccessUrl("/ciao",true)
				.failureUrl("/login.html?status=failed") */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
	    .antMatchers("/h2-console/**").permitAll()
	    .antMatchers("/frontend/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/loginPage.html")
		.loginProcessingUrl("/login")
		.permitAll().and().logout()
		.logoutUrl("/logout")
		.permitAll();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("password").roles("ADMIN")
				.build();

		UserDetails user1 = User.withDefaultPasswordEncoder().username("user1").password("password").roles("USER")
				.build();

		UserDetails user2 = User.withDefaultPasswordEncoder().username("user2").password("password").roles("USER")
				.build();

		return new InMemoryUserDetailsManager(admin, user1,user2);
	}

}
