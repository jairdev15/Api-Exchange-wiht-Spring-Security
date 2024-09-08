package com.example.exchangeapi.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.exchangeapi.application.dto.ExchangeRateRequest;
import com.example.exchangeapi.application.dto.ExchangeRateResponse;
import com.example.exchangeapi.application.dto.ExchangeTransactionDTO;
import com.example.exchangeapi.application.dto.ExchangeTransactionListResponse;
import com.example.exchangeapi.domain.ExchangeRate;
import com.example.exchangeapi.domain.ExchangeTransaction;
import com.example.exchangeapi.infrastructure.feign.ExchangeRateClient;
import com.example.exchangeapi.infrastructure.persistence.ExchangeRateRepository;
import com.example.exchangeapi.infrastructure.persistence.ExchangeTransactionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExchangeRateService {

	private final ExchangeRateClient exchangeRateClient;
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeTransactionRepository exchangeTransactionRepository;
    private final ObjectMapper objectMapper;

    public ExchangeRateService(ExchangeRateClient exchangeRateClient,
                               ExchangeRateRepository exchangeRateRepository,
                               ExchangeTransactionRepository exchangeTransactionRepository,
                               ObjectMapper objectMapper) {
        this.exchangeRateClient = exchangeRateClient;
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeTransactionRepository = exchangeTransactionRepository;
        this.objectMapper = objectMapper;
    }

    public ExchangeRateResponse convertCurrency(ExchangeRateRequest request) throws Exception {
        String ratesJson = exchangeRateClient.getLatestRates(request.getFromCurrency());
        JsonNode ratesNode = objectMapper.readTree(ratesJson);
        
        double rate = ratesNode.get("rates").get(request.getToCurrency()).asDouble();
        
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setBaseCurrency(request.getFromCurrency());
        exchangeRate.setTargetCurrency(request.getToCurrency());
        exchangeRate.setRate(rate);
        exchangeRateRepository.save(exchangeRate);
        
        double convertedAmount = request.getAmount() * rate;
        
        ExchangeTransaction transaction = new ExchangeTransaction();
        transaction.setOriginalAmount(request.getAmount());
        transaction.setOriginalCurrency(request.getFromCurrency());
        transaction.setConvertedAmount(convertedAmount);
        transaction.setTargetCurrency(request.getToCurrency());
        transaction.setExchangeRate(rate);
        exchangeTransactionRepository.save(transaction);
        
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setOriginalAmount(request.getAmount());
        response.setConvertedAmount(convertedAmount);
        response.setFromCurrency(request.getFromCurrency());
        response.setToCurrency(request.getToCurrency());
        response.setExchangeRate(rate);
        
        return response;
    }
    
    //
    public ExchangeTransactionListResponse getAllExchangeTransactions() {
        List<ExchangeTransaction> transactions = exchangeTransactionRepository.findAll();
        List<ExchangeTransactionDTO> transactionDTOs = transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ExchangeTransactionListResponse(transactionDTOs);
    }

    private ExchangeTransactionDTO convertToDTO(ExchangeTransaction transaction) {
        return new ExchangeTransactionDTO(
                transaction.getId(),
                transaction.getOriginalAmount(),
                transaction.getOriginalCurrency(),
                transaction.getConvertedAmount(),
                transaction.getTargetCurrency(),
                transaction.getExchangeRate()
        );
    }
    
}
