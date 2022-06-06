package com.example.clients;

import com.example.dto.GifDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="gifsClient", url="${gif.url}")
public interface GifsFeignClient {

    @GetMapping(params = {"api_key", "q"})
    public GifDTO getGif(@RequestParam("api_key") String appId,
                         @RequestParam("q") String query);

}
