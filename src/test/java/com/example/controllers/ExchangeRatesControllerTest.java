package com.example.controllers;

import com.example.clients.ExchangeRatesFeignClient;
import com.example.clients.GifsFeignClient;
import com.example.dto.ExchangeRatesDTO;
import com.example.dto.GifDTO;
import com.example.services.ExchangeRatesService;
import com.example.services.GifsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExchangeRatesController.class)
class ExchangeRatesControllerTest {

    @MockBean
    private ExchangeRatesFeignClient exchangeRatesFeignClient;
    @MockBean
    private GifsFeignClient gifsFeignClient;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ExchangeRatesService exchangeRatesService;
    @MockBean
    private GifsService gifService;

    @Test
    void testGifCurrencyController() throws Exception {
        GifDTO gif = new GifDTO();
        gif.setData((Map<String, Object>) new HashMap<>()
                .put("image_original_url", "https://giphy.com/gifs/messed-up-that-feel-i4y79WnfBKlQNjX65K"));
        ExchangeRatesDTO today = new ExchangeRatesDTO();
        today.setRates((Map<String, Double>) new HashMap<>().put("RUB", 63.250003));
        ExchangeRatesDTO yesterday = new ExchangeRatesDTO();
        yesterday.setRates((Map<String, Double>) new HashMap<>().put("RUB", 63.360006));
        Mockito.when(exchangeRatesFeignClient.getLatestExchangeRates(any(), any(), any())).thenReturn(today);
        Mockito.when(exchangeRatesFeignClient.getHistoricalExchangeRates(any(), any(), any(), any())).thenReturn(yesterday);
        Mockito.when(gifsFeignClient.getGif(any(), any())).thenReturn(gif);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/RUB"))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertNotNull(mvcResult.toString());
    }

}