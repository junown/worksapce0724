package com.kh.server.controller;

import com.kh.server.dto.ProductRequestDto;
import com.kh.server.dto.ProductResponseDto;
import com.kh.server.dto.ProductUpdateRequestDto;
import com.kh.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductRequestDto dto, @RequestParam Long sellerId) {
        System.out.println(" 상품 등록 요청 옴: " + dto.getName());
        try {
            ProductResponseDto responseDto = productService.create(dto, sellerId);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        System.out.println(" 전체 상품 조회 요청 옴");
        List<ProductResponseDto> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        System.out.println(" 상품 상세 조회 요청 옴: " + id);
        try {
            ProductResponseDto product = productService.getById(id);
            return ResponseEntity.ok(product);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDto>> getByCategory(@PathVariable String category) {
        System.out.println(" 카테고리별 상품 조회 요청 옴: " + category);
        List<ProductResponseDto> products = productService.getByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{category}/status/{status}")
    public ResponseEntity<List<ProductResponseDto>> getByCategoryAndStatus(
            @PathVariable String category,
            @PathVariable String status) {
        System.out.println(" 카테고리 및 상태별 상품 조회 요청 옴: " + category + ", " + status);
        List<ProductResponseDto> products = productService.getByCategoryAndStatus(category, status);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> search(@RequestParam String keyword) {
        System.out.println(" 상품 검색 요청 옴: " + keyword);
        List<ProductResponseDto> products = productService.searchByName(keyword);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequestDto dto,
            @RequestParam Long sellerId) {
        System.out.println(" 상품 수정 요청 옴: " + id);
        try {
            ProductResponseDto responseDto = productService.update(id, dto, sellerId);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        System.out.println(" 상품 상태 변경 요청 옴: " + id + " -> " + status);
        try {
            ProductResponseDto responseDto = productService.updateStatus(id, status);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestParam Long sellerId) {
        System.out.println(" 상품 삭제 요청 옴: " + id);
        try {
            productService.delete(id, sellerId);
            return ResponseEntity.ok("상품이 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

