package com.example.services;

import com.example.clients.ExchangeRatesFeignClient;
import com.example.dto.ExchangeRatesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ExchangeRatesServiceImpl  implements ExchangeRatesService{

    private ExchangeRatesFeignClient client;

    public ExchangeRatesServiceImpl(ExchangeRatesFeignClient client) {
        this.client = client;
    }

    @Value("${exchangerates.app_id}")
    private String appId;


    public Double getExchangeRatesGap(String currency) {
        Double currentRate = 0.;
        Double recentRate = 0.;
        try {
            currentRate = client.getLatestExchangeRates(appId).getRates().get(currency.toUpperCase());
            recentRate = client.getHistoricalExchangeRates(getYesterdayDate(), appId).getRates().get(currency.toUpperCase());
        } catch (Exception e) {
            throw new IncorrectCurrencyException();
        }
        return currentRate - recentRate;
    }

    private String getYesterdayDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return formatter.format(yesterday);
    }
}
