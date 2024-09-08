package com.example.exchangeapi.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchangeapi.application.service.UserService;
import com.example.exchangeapi.domain.UserEntity;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping()
	public ResponseEntity<?> AllUser(){
		return ResponseEntity.ok(userService.findAll());
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody UserEntity user){
		return ResponseEntity.ok(userService.save(user));
	}
	
}
