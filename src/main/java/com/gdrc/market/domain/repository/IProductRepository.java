package com.gdrc.market.domain.repository;

import com.gdrc.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    List<Product> getAll();

    Optional<List<Product>> getByCategory(int categoryId);

    Optional<List<Product>> getByQuantityLessThan(int stockQuantity);

    Optional<Product> getProduct(int productId);

    Product save(Product product);

    void delete(int productId);
}
