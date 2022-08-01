package com.blog.safety.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
	public class SecurityConfig {
		private static final String[] AUTH_LIST = {
		        "/",
		        "/posts",
		        "/posts/{id}"
		    };
	
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http.csrf().disable().authorizeRequests()
	     .antMatchers(AUTH_LIST).permitAll()
	     .anyRequest().authenticated()
	     .and().formLogin().permitAll()
	     .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		 return http.build();
	 }
	 
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		 UserDetails user = User.withUsername("jose")
	                .password("{noop}123").roles("ADMIN")
		            .build();
		        return new InMemoryUserDetailsManager(user);
		 
	 }
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().antMatchers("/css/**");
	}
}

