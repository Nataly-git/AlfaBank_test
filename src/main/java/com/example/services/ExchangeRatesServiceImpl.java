package com.example.services;

import com.example.clients.ExchangeRatesFeignClient;
import com.example.dto.ExchangeRatesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExchangeRatesServiceImpl  implements ExchangeRatesService{

    private ExchangeRatesFeignClient client;

    public ExchangeRatesServiceImpl(ExchangeRatesFeignClient client) {
        this.client = client;
    }

    @Value("${exchangerates.app_id}")
    private String appId;

    @Value("${exchangerates.currency}")
    private String currency;


    @Override
    public Double getExchangeRatesGap() {
        Double currentRate = client.getLatestExchangeRates(appId).getRates().get(currency);
        Double recentRate = client.getHistoricalExchangeRates(getYesterdayDate(), appId).getRates().get(currentRate);
        return currentRate - recentRate;
    }

    private String getYesterdayDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return formatter.format(yesterday);
    }
}
