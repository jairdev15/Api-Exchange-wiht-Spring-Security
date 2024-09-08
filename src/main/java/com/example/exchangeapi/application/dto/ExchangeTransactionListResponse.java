package com.example.exchangeapi.application.dto;

import java.util.List;

public class ExchangeTransactionListResponse {

	 private List<ExchangeTransactionDTO> transactions;

	    public ExchangeTransactionListResponse(List<ExchangeTransactionDTO> transactions) {
	        this.transactions = transactions;
	    }

	    public List<ExchangeTransactionDTO> getTransactions() {
	        return transactions;
	    }

	    public void setTransactions(List<ExchangeTransactionDTO> transactions) {
	        this.transactions = transactions;
	    }
	    
}
