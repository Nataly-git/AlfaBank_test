package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class GifDTO {
    private List<ImageObject> data;

    public ImageObject getRandomGif() {
        return data.get(getRandomNumber());
    }

    private int getRandomNumber() {
        int max = data.size() - 1;
        int randomNumber = (int) (Math.random() * max);
        return randomNumber;
    }
}
