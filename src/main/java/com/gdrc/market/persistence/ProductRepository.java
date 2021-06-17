package com.gdrc.market.persistence;

import com.gdrc.market.persistence.crud.ProductCrudRepository;
import com.gdrc.market.persistence.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
@Repository:
    Con esta anotacion le indicamos a Spring que esta clase se
    encargara de interactuar directamente con la db.
 */
@Repository
public class ProductRepository {
    private ProductCrudRepository productCrudRepository;

    public List<ProductEntity> getAll() {
        return (List<ProductEntity>) productCrudRepository.findAll();
    }

    public List<ProductEntity> getByCategory(int categoryId) {
        return productCrudRepository.findByCategoryIdOrderByAsc(categoryId);
    }

    public Optional<List<ProductEntity>> getByQuantityLessThan(int stockQuantity) {
        return productCrudRepository.findByStockQuantityLessThanAndState(stockQuantity, true);
    }

    public Optional<ProductEntity> getProduct(int productId) {
        return productCrudRepository.findById(productId);
    }

    public ProductEntity save(ProductEntity productEntity) {
        return productCrudRepository.save(productEntity);
    }
    public void delete(int productId) {
        productCrudRepository.deleteById(productId);
    }
}
