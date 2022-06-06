package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class GifDTO {
    private Map<String, Object> data;

//    public ImageObject getRandomGif() {
//        return data.get(getRandomNumber());
//    }
//
//    private int getRandomNumber() {
//        int max = data.size() - 1;
//        int randomNumber = (int) (Math.random() * max);
//        return randomNumber;
//    }
}
