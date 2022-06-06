package com.example.services;


public interface ExchangeRatesService {

    int getExchangeRatesGap(String currency);

    Double getDateRate(String symbols, String date);

    Double getTodayRate(String symbols);
}
