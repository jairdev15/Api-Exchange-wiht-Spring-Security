package com.example.exchangeapi.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.exchangeapi.application.dto.AuthRequest;
import com.example.exchangeapi.application.dto.AuthResponse;
import com.example.exchangeapi.infrastructure.security.config.JwtProvider;

@Service
public class AuthService {

	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public AuthResponse authenticate(AuthRequest authRequest) {
		try {
			this.authenticateSecurity(authRequest.getUsername(), authRequest.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		String token = this.jwtProvider.generateToken(userDetails);
		return new AuthResponse(token);
	}
	
	
	private void authenticateSecurity(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password)
			);
		} catch (DisabledException e) {
			e.printStackTrace();
			throw new Exception("Disable User. " + e.getMessage());
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials. " + e.getMessage());
		}
	}
	
}
