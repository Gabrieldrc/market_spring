package com.gdrc.market.web.controller;

import com.gdrc.market.domain.Product;
import com.gdrc.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
@RestController:
    Es la anotacion que indica que la clase es un
    controlador
@RequestMapping:
    Es la anotacion que indica a que request de url
    el controlador se encargara en responder
@GetMapping:
    Es la anotacion para indicar que el metodo se
    encargara de las llamadas en GET d un path
@PathVariable:
    Le indicamos que lo que se recibira como
    parametro es la variable del path llamada
    "..."
@PostMapping:
    Es la anotacion para indicar que el metodo se
    encargara de las llamadas en POST d un path
@RequestBody:
    Le indicamos que lo que se recibira como
    parametro es el objeto del body del request
@DeleteMapping:
    Es la anotacion para indicar que el metodo se
    encargara de las llamadas en DELETE d un path
 */

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") int productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/category/{categoryId}")
    public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int productId) {
        return productService.delete(productId);
    }
}
