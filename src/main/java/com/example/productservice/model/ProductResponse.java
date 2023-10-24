package com.example.productservice.model;

import com.example.productservice.entity.Product;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Builder
@JsonNaming(SnakeCaseStrategy.class)
public record ProductResponse(
    String productName,
    Long productId,
    Long quantity,
    Long price
) {

    // entity 를 record 로 변환하는 메서드
    public static ProductResponse fromEntityToRecord(Product product) {
        return ProductResponse.builder()
            .productId(product.getId())
            .productName(product.getName())
            .quantity(product.getQuantity())
            .price(product.getPrice())
            .build();
    }
}
