package com.kh.server.service;

import com.kh.server.dto.ProductRequestDto;
import com.kh.server.dto.ProductResponseDto;
import com.kh.server.dto.ProductUpdateRequestDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto create(ProductRequestDto dto, Long sellerId);
    List<ProductResponseDto> getAll();
    ProductResponseDto getById(Long id);
    List<ProductResponseDto> getByCategory(String category);
    List<ProductResponseDto> getByCategoryAndStatus(String category, String status);
    List<ProductResponseDto> searchByName(String keyword);
    ProductResponseDto update(Long id, ProductUpdateRequestDto dto, Long sellerId);
    ProductResponseDto updateStatus(Long id, String status);
    void delete(Long id, Long sellerId);
}