package com.example.exchangeapi.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.exchangeapi.domain.ExchangeTransaction;

public interface ExchangeTransactionRepository extends JpaRepository<ExchangeTransaction, Long>{
	
}
