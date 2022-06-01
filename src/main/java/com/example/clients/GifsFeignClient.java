package com.example.clients;

import com.example.dto.GifDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="gifsClient", url="${gif.url}")
public interface GifsFeignClient {

    @GetMapping("/random")
    public GifDTO getGif(@RequestParam("app_id") String appId,
                         @RequestParam("tag") String tag);

}
