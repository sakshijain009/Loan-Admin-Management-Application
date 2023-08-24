package com.training.loanapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.training.loanapplication.config.JwtTokenUtil;
import com.training.loanapplication.model.Employee;
import com.training.loanapplication.model.JwtRequest;
import com.training.loanapplication.model.JwtResponse;
import com.training.loanapplication.service.JwtUserDetailsService;

public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		System.out.println("user details "+userDetails);

		final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("token :"+token);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@GetMapping("/welcome")
	public String showMessage()  {
		return "You are authorized";
	}
	
//	@PostMapping("/authenticate")
//	public String generateToken(@RequestBody JwtRequest jwtRequest) {
//		return null;
//		
//	}
	
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody Employee emp) throws Exception {
		return ResponseEntity.ok(userDetailsService.saveEmployee(emp));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials", e);
		}
	}
}
