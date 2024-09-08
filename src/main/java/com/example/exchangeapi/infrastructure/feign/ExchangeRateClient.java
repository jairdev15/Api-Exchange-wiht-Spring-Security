package com.example.exchangeapi.infrastructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchangeRateApi", url = "https://open.er-api.com/v6")
public interface ExchangeRateClient {
	
	@GetMapping("/latest/{currency}")
    String getLatestRates(@PathVariable("currency") String currency);

}
