package com.kh.server.service;

import com.kh.server.dto.ProductResponseDto;

public interface OrderService {
    ProductResponseDto purchase(Long productId, Long buyerId);
}

