package com.kh.server.service;

import com.kh.server.dto.ProductResponseDto;
import com.kh.server.entity.Member;
import com.kh.server.entity.Order;
import com.kh.server.entity.Product;
import com.kh.server.repository.MemberRepository;
import com.kh.server.repository.OrderRepository;
import com.kh.server.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public ProductResponseDto purchase(Long productId, Long buyerId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        Member buyer = memberRepository.findById(buyerId)
                .orElseThrow(() -> new IllegalArgumentException("구매자를 찾을 수 없습니다."));

        // 판매자가 자신의 상품을 구매하려는 경우
        if (product.getSeller().getId().equals(buyerId)) {
            throw new IllegalArgumentException("자신의 상품은 구매할 수 없습니다.");
        }

        // 이미 판매완료된 상품인 경우
        if ("판매완료".equals(product.getStatus())) {
            throw new IllegalArgumentException("이미 판매완료된 상품입니다.");
        }

        // Order 생성
        Order order = Order.builder()
                .buyer(buyer)
                .product(product)
                .price(product.getPrice())
                .status("주문완료")
                .build();
        orderRepository.save(order);

        // 상품 상태를 판매완료로 변경
        product.setStatus("판매완료");
        Product updatedProduct = productRepository.save(product);

        return new ProductResponseDto(updatedProduct);
    }
}

