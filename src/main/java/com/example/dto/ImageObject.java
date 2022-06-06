package com.example.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ImageObject {
    private String type;
    private String id;
    private Map<String, ImageProperties> images;

}
