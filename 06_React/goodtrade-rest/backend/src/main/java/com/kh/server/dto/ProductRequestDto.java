package com.kh.server.dto;

import com.kh.server.entity.Product;
import lombok.Data;
import java.util.List;

@Data
public class ProductRequestDto {
    private String name;
    private int price;
    private String category;
    private String description;
    private List<String> images;

    public Product toEntity(Long sellerId) {
        String imagesJson = images != null && !images.isEmpty() 
            ? String.join(",", images) 
            : "";
        
        return Product.builder()
                .name(name)
                .price(price)
                .category(category)
                .description(description)
                .sellerId(sellerId)
                .status("판매중")
                .images(imagesJson)
                .build();
    }
}

