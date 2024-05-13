package com.xyz.EHub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
		return  httpSecurity.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(registry -> {
					registry.requestMatchers("/user/**").hasRole("CREATOR");
					registry.requestMatchers("/learner/**").hasRole("LEARNER");
					registry.requestMatchers("/**","/user/**").permitAll();
					registry.anyRequest().authenticated();
				})
				.formLogin(httpSecurityFormLoginConfigurer ->
						httpSecurityFormLoginConfigurer.loginPage("/login").defaultSuccessUrl("/").permitAll())
				.build();

	}

}
