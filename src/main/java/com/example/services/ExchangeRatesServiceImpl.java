package com.example.services;

import com.example.clients.ExchangeRatesFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

//    public String getTagDependOnExchangeRates(String currency) {
//        Double gap = getExchangeRatesGap(currency);
//        if(gap > 0) {
//            return "rich";
//        }
//        else if(gap < 0) {
//            return "broke";
//        }
//        else return "equal";
//    }

    public int getExchangeRatesGap(String currency) {
        BigDecimal currentRate = BigDecimal.valueOf(client.getLatestExchangeRates(appId, currency).getRates().get(currency.toUpperCase()));
        BigDecimal recentRate = BigDecimal.valueOf(client.getHistoricalExchangeRates(getYesterdayDate(), appId, currency).getRates().get(currency.toUpperCase()));
        return currentRate.compareTo(recentRate);
    }

    private String getYesterdayDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return formatter.format(yesterday);
    }
}
