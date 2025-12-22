package com.kh.server.dto;

import com.kh.server.entity.Product;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private String category;
    private String status;
    private Long seller; 
    private String description;
    private List<String> images;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.status = product.getStatus();
        this.seller = product.getSeller() != null ? product.getSeller().getId() : null;
        this.description = product.getDescription();
        
        if (product.getImages() != null && !product.getImages().isEmpty()) {
            String[] imageArray = product.getImages().split(",");
            this.images = new ArrayList<>();
            for (String img : imageArray) {
                if (!img.trim().isEmpty()) {
                    this.images.add(img.trim());
                }
            }
        } else {
            this.images = new ArrayList<>();
        }
    }
}

