package com.example.services;

import org.springframework.http.ResponseEntity;

public interface GifsService {
    public ResponseEntity<byte[]> getGifByUrl(String query);
}
