package com.example.controllers;

import com.example.services.ExchangeRatesService;
import com.example.services.GifsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExchangeRatesController {

    private ExchangeRatesService exchangeRatesService;
    private GifsService gifsService;

    @Autowired
    public ExchangeRatesController(ExchangeRatesService exchangeRatesService, GifsService gifsService) {
        this.exchangeRatesService = exchangeRatesService;
        this.gifsService = gifsService;
    }

    @GetMapping("/gifs/{symbols}")
    public ResponseEntity<byte[]> getGifByCode(@PathVariable("symbols") String symbols) {
        return gifsService.getGifByUrl(exchangeRatesService.getTagDependOnExchangeRates(symbols));
    }
}
