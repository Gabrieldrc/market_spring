package com.gdrc.market.persistence;

import com.gdrc.market.persistence.crud.ProductCrudRepository;
import com.gdrc.market.persistence.entity.Product;
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

    public List<Product> getAll() {
        return (List<Product>) productCrudRepository.findAll();
    }

    public List<Product> getByCategory(int categoryId) {
        return productCrudRepository.findByCategoryIdOrderByAsc(categoryId);
    }

    public Optional<List<Product>> getByQuantityLessThan(int stockQuantity) {
        return productCrudRepository.findByStockQuantityLessThanAndState(stockQuantity, true);
    }

    public Optional<Product> getProduct(int productId) {
        return productCrudRepository.findById(productId);
    }

    public Product save(Product product) {
        return productCrudRepository.save(product);
    }
    public void delete(int productId) {
        productCrudRepository.deleteById(productId);
    }
}
