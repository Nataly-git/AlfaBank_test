package com.example.services;

import com.example.clients.ExchangeRatesFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ExchangeRatesServiceImpl  implements ExchangeRatesService{

    private ExchangeRatesFeignClient client;

    @Value("${exchangerates.app_id}")
    private String appId;

    @Value("${exchangerates.base}")
    private String base;

    @Autowired
    public ExchangeRatesServiceImpl(ExchangeRatesFeignClient client) {
        this.client = client;
    }

    public int getExchangeRatesGap(String currency) {
        BigDecimal currentRate = BigDecimal.valueOf(getTodayRate(currency));
        BigDecimal recentRate = BigDecimal.valueOf(getDateRate(currency, getYesterdayDate()));
        return currentRate.compareTo(recentRate);
    }

    private String getYesterdayDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return formatter.format(yesterday);
    }

    public Double getDateRate(String symbols, String date) {
        return client.getHistoricalExchangeRates(date, appId, base, symbols).getRates().get(symbols.toUpperCase());
    }

    public Double getTodayRate(String symbols) {
        return client.getLatestExchangeRates(appId, base, symbols).getRates().get(symbols.toUpperCase());
    }
}
