package com.example.productservice.controller;

import com.example.productservice.model.ProductRequest;
import com.example.productservice.model.ProductResponse;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    // @Qualifier("productServiceImpl")
    private final ProductService productService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
        Long productId = productService.addProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer') || hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long productId) {
        ProductResponse productResponse = productService.getProductById(productId);
        log.info("[i] ProductResponse 는 다음과 같습니다. [{}]", productResponse);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Integer> reduceQuantity(
        @PathVariable("id") Long productId,
        @RequestParam Long quantity
    ) {
        productService.reduceQuantity(productId, quantity);
        return ResponseEntity.ok(1);
    }


}
