package com.training.loanapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.training.loanapplication.service.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtUserDetailsService usrDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usrDetailsService).passwordEncoder(passwordEncoder);
	}



	protected void configure(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests -> requests
//        		.requestMatchers("/register").authenticated()
                .requestMatchers("/welcome").permitAll()
//                .requestMatchers("/auth/**").permitAll()
//                .requestMatchers("/api/v1/get/user/**").permitAll()
//                .requestMatchers("/api/v1/sendUser").permitAll()
//                .anyRequest()
//                .authenticated()
                )
        .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//		return http.build();
	}
}
