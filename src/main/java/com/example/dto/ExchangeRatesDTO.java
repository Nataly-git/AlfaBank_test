package com.example.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRatesDTO {
    private String base;
    private Map<String, Double> rates;

}
