package com.example.controllers;

import com.example.dto.GifDTO;
import com.example.services.ExchangeRatesService;
import com.example.services.GifsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ExchangeRatesController {

    private ExchangeRatesService exchangeRatesService;
    private GifsService gifsService;

    @GetMapping("/gifs/{code}")
    public GifDTO getGifByCode(@PathBariable("code") String code) {

    }
}
