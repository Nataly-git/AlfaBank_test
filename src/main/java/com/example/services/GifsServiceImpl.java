package com.example.services;

import com.example.clients.GifsFeignClient;
import com.example.dto.GifDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class GifsServiceImpl implements GifsService{

    private GifsFeignClient client;

    @Value("${gif.api_key}")
    private String appId;

    @Autowired
    public GifsServiceImpl(GifsFeignClient client) {
        this.client = client;
    }

    public String getGifUrl(String tag) {
        GifDTO gif = client.getGif(appId, tag);
        return (String) gif.getData().get("url");
    }
}
