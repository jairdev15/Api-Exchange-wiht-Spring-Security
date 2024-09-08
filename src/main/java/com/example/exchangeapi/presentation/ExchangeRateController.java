package com.example.exchangeapi.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchangeapi.application.dto.ExchangeRateRequest;
import com.example.exchangeapi.application.dto.ExchangeRateResponse;
import com.example.exchangeapi.application.dto.ExchangeTransactionListResponse;
import com.example.exchangeapi.application.service.ExchangeRateService;

@RestController
public class ExchangeRateController {

	private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @PostMapping("/convert")
    public ExchangeRateResponse convertCurrency(@RequestBody ExchangeRateRequest request) throws Exception {
        return exchangeRateService.convertCurrency(request);
    }
    
    @GetMapping("/transactions")
    public ExchangeTransactionListResponse getAllTransactions() {
        return exchangeRateService.getAllExchangeTransactions();
    }
}
