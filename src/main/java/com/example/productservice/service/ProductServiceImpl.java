package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.exception.ProductServiceCustomException;
import com.example.productservice.model.ProductRequest;
import com.example.productservice.model.ProductResponse;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Long addProduct(ProductRequest productRequest) {
        log.info("[i] 제품을 등록합니다. ");
        Product productPS = new Product(productRequest);
        productRepository.save(productPS);
        log.info("[i] 새로운 제품이 등록되었습니다. ");

        return productPS.getId();
    }

    @Override
    @Transactional
    public ProductResponse getProductById(Long productId) {
        log.info("[i] 제품아이디 [{}] 를 찾습니다. ", productId);
        Product productPS = productRepository.findById(productId).orElseThrow(
            () -> new ProductServiceCustomException(productId + "에 해당하는 제품이 존재하지 않습니다.", "PRODUCT_NOT_FOUND")
        );
        log.info("[i] 제품아이디 [{}] 를 찾았습니다.", productId);

        log.info("[i] 엔티티를 레코드로 변환한 결과는 다음과 같습니다. [{}]", ProductResponse.of(productPS));

        return ProductResponse.of(productPS);
    }

    @Override
    @Transactional
    public void reduceQuantity(Long productId, Long quantity) {
        log.info("[i] 제품번호 [{}] 의 재고를 [{}] 만큼 감소시킵니다.", productId, quantity);

        Product productPS = productRepository
            .findById(productId)
            .orElseThrow(() -> new ProductServiceCustomException("[" + productId + "] 가 존재하지 않습니다.", "PRODUCT_NOT_FOUND"));

        // 재고가 부족한 경우 예외처리
        if (productPS.getQuantity() < quantity) {
            throw new ProductServiceCustomException("재고가 부족합니다.", "INSUFFICENT_QUANTITY");
        }

        // 제품의 수량을 감소시킵니다.
        productPS.reduceProductQuantity(quantity);
        log.info("[i] 제품번호 [{}] 의 재고가 [{}] 만큼 감소되었습니다.", productId, quantity);
    }
}
