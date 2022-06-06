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

    public ResponseEntity<byte[]> getGifByUrl(String query) {
        return getGif(getGifUrlDependsOnExchangeRates(query));
    }

    private String getGifUrlDependsOnExchangeRates(String query) {
        return client.getGif(appId, query)
                .getRandomGif()
                .getImages()
                .get("original")
                .getUrl();
    }

    private ResponseEntity<byte[]> getGif(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.IMAGE_GIF));
        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
    }
}
