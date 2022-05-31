package com.example.clients;

import com.example.dto.ExchangeRatesDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="exchangeRatesClient", url="${exchangerates.url}")
public interface ExchangeRatesFeignClient {

    @GetMapping("/latest.json")
    public ExchangeRatesDTO getLatestExchangeRates(@RequestParam ("app_id") String appId);

    @GetMapping("/historical/{date}.json")
    public ExchangeRatesDTO getHistoricalExchangeRates(@PathVariable("date") String date,
                                                       @RequestParam ("app_id") String appId);

}
