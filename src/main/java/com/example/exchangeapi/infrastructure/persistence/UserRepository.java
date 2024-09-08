package com.example.exchangeapi.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.exchangeapi.domain.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	public Optional<UserEntity> findByUsername(String username);
	
}
