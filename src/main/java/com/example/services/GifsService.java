package com.example.services;

import org.springframework.http.ResponseEntity;

public interface GifsService {
    String getGifUrl(String tag);
}
