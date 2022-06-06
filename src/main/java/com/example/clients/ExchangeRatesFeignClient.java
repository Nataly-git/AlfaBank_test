package com.example.clients;

import com.example.dto.ExchangeRatesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="exchangerates", url="${exchangerates.url}")
public interface ExchangeRatesFeignClient {

    @GetMapping("/latest.json")
    ExchangeRatesDTO getLatestExchangeRates(@RequestParam("app_id") String appId,
                                                   @RequestParam("symbols") String symbols);

    @GetMapping("/historical/{date}.json")
    ExchangeRatesDTO getHistoricalExchangeRates(@PathVariable("date") String date,
                                                       @RequestParam ("app_id") String appId,
                                                       @RequestParam("symbols") String symbols);

}
