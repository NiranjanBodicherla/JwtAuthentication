package com.Jwt.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Jwt.demo.dao.RolesDao;
import com.Jwt.demo.jwtutils.JwtUserDetailsService;
import com.Jwt.demo.jwtutils.TokenManager;
import com.Jwt.demo.model.JwtRequest;
import com.Jwt.demo.model.JwtResponse;
import com.Jwt.demo.model.Role;
import com.Jwt.demo.model.User;



@RestController
public class JwtController {
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RolesDao rolesDao;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = tokenManager.generateToken(userDetails);
		System.out.println(token);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	
	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@Validated @RequestBody User user) throws Exception {
		User saveUser = this.userDetailsService.saveUser(user);
		Role role = this.rolesDao.findById(2).get();
		user.getRoles().add(role);
		return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	

}
