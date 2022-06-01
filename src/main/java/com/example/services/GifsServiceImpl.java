package com.example.services;

import com.example.clients.GifsFeignClient;
import com.example.dto.GifDTO;
import org.springframework.stereotype.Service;

@Service
public class GifsServiceImpl implements GifsService{

    private GifsFeignClient client;

    public GifsServiceImpl(GifsFeignClient client) {
        this.client = client;
    }

    public GifDTO getGifDependsOnExchangeRates(String tag) {
        return client.getGif("${Bm5a3IG8uL1WvffAhOH9t99kjg7Mi0GI}", tag);
    }

}
