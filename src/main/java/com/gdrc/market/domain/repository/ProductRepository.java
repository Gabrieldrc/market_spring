package com.gdrc.market.domain.repository;

import com.gdrc.market.domain.ProductMapper;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<ProductMapper> getAll();

    Optional<List<ProductMapper>> getByCategory(int categoryId);

    Optional<List<ProductMapper>> getByQuantityLessThan(int stockQuantity);

    Optional<ProductMapper> getProduct(int productId);

    ProductMapper save(ProductMapper product);

    void delete(int productId);
}
