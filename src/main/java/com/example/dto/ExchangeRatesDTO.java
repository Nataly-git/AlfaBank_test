package com.example.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.Map;

@Data
public class ExchangeRatesDTO {
    private String disclaimer;
    private String license;
    private BigInteger timestamp;
    private String base;
    private Map<String, Double> rates;
}
