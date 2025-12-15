package com.kh.server.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductUpdateRequestDto {
    private String name;
    private int price;
    private String category;
    private String status;
    private String description;
    private List<String> images;
}

