package com.example.exchangeapi.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchangeapi.application.dto.AuthRequest;
import com.example.exchangeapi.application.service.AuthService;

@RestController
@CrossOrigin({"*"})
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping
	private ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest){
		return ResponseEntity.ok(authService.authenticate(authRequest));
	}
	
	
}
