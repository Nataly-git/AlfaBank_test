package com.example.services;

import com.example.dto.GifDTO;

public interface GifsService {
    public GifDTO getGifDependsOnExchangeRates(String tag);
}
