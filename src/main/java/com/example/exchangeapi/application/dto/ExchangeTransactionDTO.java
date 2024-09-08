package com.example.exchangeapi.application.dto;

public class ExchangeTransactionDTO {

	private Long id;
    private Double originalAmount;
    private String originalCurrency;
    private Double convertedAmount;
    private String targetCurrency;
    private Double exchangeRate;

    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Double getOriginalAmount() {
		return originalAmount;
	}


	public void setOriginalAmount(Double originalAmount) {
		this.originalAmount = originalAmount;
	}


	public String getOriginalCurrency() {
		return originalCurrency;
	}


	public void setOriginalCurrency(String originalCurrency) {
		this.originalCurrency = originalCurrency;
	}


	public Double getConvertedAmount() {
		return convertedAmount;
	}


	public void setConvertedAmount(Double convertedAmount) {
		this.convertedAmount = convertedAmount;
	}


	public String getTargetCurrency() {
		return targetCurrency;
	}


	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}


	public Double getExchangeRate() {
		return exchangeRate;
	}


	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public ExchangeTransactionDTO(Long id, Double originalAmount, String originalCurrency, Double convertedAmount, String targetCurrency, Double exchangeRate) {
        this.id = id;
        this.originalAmount = originalAmount;
        this.originalCurrency = originalCurrency;
        this.convertedAmount = convertedAmount;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
    }
}
