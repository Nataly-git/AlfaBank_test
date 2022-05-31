package com.example.services;

import com.example.clients.ExchangeRatesFeignClient;
import com.example.dto.ExchangeRatesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRatesServiceImpl  implements ExchangeRatesService{

    private ExchangeRatesFeignClient client;

    public ExchangeRatesServiceImpl(ExchangeRatesFeignClient client) {
        this.client = client;
    }

    @Override
    public List<ExchangeRatesDTO> getExchangeRates() {

        return null;
    }
}
