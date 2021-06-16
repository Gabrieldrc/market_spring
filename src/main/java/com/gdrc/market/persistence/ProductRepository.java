package com.gdrc.market.persistence;

import com.gdrc.market.persistence.crud.ProductCrudRepository;
import com.gdrc.market.persistence.entity.Product;

import java.util.List;

public class ProductRepository {
    private ProductCrudRepository productCrudRepository;

    public List<Product> getAll() {
        return (List<Product>) productCrudRepository.findAll();
    }
}
