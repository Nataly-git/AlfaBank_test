package com.example.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

@JsonTest
class DTOTest {
    @Autowired
    private JacksonTester<ExchangeRatesDTO> exchangeRatesDtoJacksonTester;
    @Autowired
    private JacksonTester<GifDTO> gifDtoJacksonTester;

    @Test
    public void testGifResponse() throws IOException {
        GifDTO gifDTO = gifDtoJacksonTester.read("/gif.json").getObject();
        Assertions.assertEquals("https://giphy.com/gifs/thesimpsons-3orif9UVRTOR3W7m1y",
                gifDTO.getData().get("url"));
    }

    @Test
    public void testCurrencyResponse() throws IOException {
        ExchangeRatesDTO exchangeRatesDTO = exchangeRatesDtoJacksonTester.read("/rate04.06.2022.json").getObject();
        Assertions.assertEquals(Double.valueOf(63.360006), exchangeRatesDTO.getRates().get("RUB"));
    }
}