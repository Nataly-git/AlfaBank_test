package com.example.services;

import com.example.clients.ExchangeRatesFeignClient;
import com.example.errors.IncorrectCurrencyException;
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

    @Value("${exchangerates.api_key}")
    private String appId;

    public String getTagDependOnExchangeRates(String currency) {
        Double gap = getExchangeRatesGap(currency);
        if(gap > 0) {
            return "rich";
        }
        else if(gap < 0) {
            return "broke";
        }
        else return "equal";
    }

    private Double getExchangeRatesGap(String currency) {
        Double currentRate = 0.;
        Double recentRate = 0.;
        try {
            currentRate = client.getLatestExchangeRates(appId, currency).getRates().get(currency.toUpperCase());
            recentRate = client.getHistoricalExchangeRates(getYesterdayDate(), appId, currency).getRates().get(currency.toUpperCase());
        } catch (Exception e) {
            throw new IncorrectCurrencyException("Currency is not valid, check input data");
        }
        return currentRate - recentRate;
    }

    private String getYesterdayDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return formatter.format(yesterday);
    }
}
