package com.gdrc.market.domain.service;

import com.gdrc.market.domain.Product;
import com.gdrc.market.domain.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
@Service:
    Es la anotacion para indicar que es un servicio.
    NOTA: tambien puede usarse Component pero la
    buena practica es poner Service ya que denota que
    es un servicio.

    En este caso inyectamos alguna implementacion de la
interfaz IProductRepository, y Spring lo que hara es
buscar implementar un componente que tenga implementada
esta interfaz.
    El servicio sirve como un negociador o intermediario
    entre el controlador y la capa del repositorio.
 */
@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        return getProduct(productId).map(product -> {
                productRepository.delete(productId);
                return true;
        }).orElse(false);
    }
}
