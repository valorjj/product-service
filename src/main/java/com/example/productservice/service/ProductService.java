package com.example.productservice.service;

import com.example.productservice.model.ProductRequest;
import com.example.productservice.model.ProductResponse;

public interface ProductService {

    Long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long productId);

    void reduceQuantity(Long productId, Long quantity);


}
