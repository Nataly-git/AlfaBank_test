package com.example.clients;

import com.example.dto.ExchangeRatesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="exchangeRatesClient", url="${exchangerates.url}")
public interface ExchangeRatesFeignClient {

    @GetMapping(value = "/latest.json", params = {"app_id", "symbols"})
    public ExchangeRatesDTO getLatestExchangeRates(@RequestParam("api_key") String appId,
                                                   @RequestParam("symbols") String symbols);

    @GetMapping(value = "/historical/{date}.json", params = {"app_id", "symbols"})
    public ExchangeRatesDTO getHistoricalExchangeRates(@PathVariable("date") String date,
                                                       @RequestParam ("api_key") String appId,
                                                       @RequestParam("symbols") String symbols);

}
