package com.example.exchangeapi.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.exchangeapi.domain.UserEntity;
import com.example.exchangeapi.infrastructure.persistence.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public Iterable<UserEntity> findAll(){
		return userRepository.findAll();
	}
	
	public UserEntity save(UserEntity user) {
		Optional<UserEntity> oUser = this.userRepository.findByUsername(user.getUsername());
		if(oUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s already exist", user.getUsername()));
		}
		String passwordEncode = encoder.encode(user.getPassword());
		user.setPassword(passwordEncode);
		user.setVigencia(true);
		
		return this.userRepository.save(user);
	}

}
