package com.example.exchangeapi.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.exchangeapi.domain.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long>{

	ExchangeRate findByBaseCurrencyAndTargetCurrency(String baseCurrency, String targetCurrency);
	
}
