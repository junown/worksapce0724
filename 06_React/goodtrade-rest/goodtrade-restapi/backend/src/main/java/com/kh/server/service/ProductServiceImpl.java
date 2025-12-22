package com.kh.server.service;

import com.kh.server.dto.ProductRequestDto;
import com.kh.server.dto.ProductResponseDto;
import com.kh.server.dto.ProductUpdateRequestDto;
import com.kh.server.entity.Member;
import com.kh.server.entity.Product;
import com.kh.server.repository.MemberRepository;
import com.kh.server.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ProductResponseDto create(ProductRequestDto dto, Long sellerId) {
        Member seller = memberRepository.findById(sellerId)
                .orElseThrow(() -> new IllegalArgumentException("판매자를 찾을 수 없습니다."));
        Product product = dto.toEntity(seller);
        Product savedProduct = productRepository.save(product);
        return new ProductResponseDto(savedProduct);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        return new ProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getByCategory(String category) {
        return productRepository.findByCategory(category).stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getByCategoryAndStatus(String category, String status) {
        if (status == null || status.equals("전체")) {
            return getByCategory(category);
        }
        return productRepository.findByCategoryAndStatus(category, status).stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> searchByName(String keyword) {
        return productRepository.findByNameContaining(keyword).stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponseDto update(Long id, ProductUpdateRequestDto dto, Long sellerId) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        // 판매자 확인
        if (product.getSeller() == null || !product.getSeller().getId().equals(sellerId)) {
            throw new IllegalArgumentException("본인의 상품만 수정할 수 있습니다.");
        }

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setStatus(dto.getStatus());
        product.setDescription(dto.getDescription());
        
        // images 리스트를 JSON 문자열로 변환
        if (dto.getImages() != null && !dto.getImages().isEmpty()) {
            String imagesJson = String.join(",", dto.getImages());
            product.setImages(imagesJson);
        }

        Product updatedProduct = productRepository.save(product);
        return new ProductResponseDto(updatedProduct);
    }

    @Override
    @Transactional
    public ProductResponseDto updateStatus(Long id, String status) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        
        product.setStatus(status);
        Product updatedProduct = productRepository.save(product);
        return new ProductResponseDto(updatedProduct);
    }

    @Override
    @Transactional
    public void delete(Long id, Long sellerId) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        // 판매자 확인
        if (product.getSeller() == null || !product.getSeller().getId().equals(sellerId)) {
            throw new IllegalArgumentException("본인의 상품만 삭제할 수 있습니다.");
        }

        productRepository.delete(product);
    }
}
