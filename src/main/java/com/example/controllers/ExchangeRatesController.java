package com.example.controllers;

import com.example.services.ExchangeRatesService;
import com.example.services.GifsService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{currency_code}")
    public String getGif(@PathVariable(name = "currency_code") String code, Model model) {
        int diff = exchangeRatesService.getExchangeRatesGap(code);
        if (diff == 0) {
            return "Курс не изменился!";
        }
        String gifUrl;
        if (diff > 0) {
            gifUrl = gifsService.getGifUrl("rich");
            model.addAttribute("gifUrl", gifUrl);
            return "<img src=\"" +
                    gifUrl + "\"/>" + " Курс стал выше!";

        } else {
            gifUrl = gifsService.getGifUrl("broke");
            model.addAttribute("gifUrl", gifUrl);
            return "<img src=\"" +
                    gifUrl + "\"/>" + " Курс стал ниже!";
        }
    }

    @ExceptionHandler({FeignException.class})
    public ResponseEntity<String> handleException(FeignException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
