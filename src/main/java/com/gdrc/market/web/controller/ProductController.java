package com.gdrc.market.web.controller;

import com.gdrc.market.domain.Product;
import com.gdrc.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
ResponseEntity:
    Es un objeto Response para mejorar nuestras
    respuestas segun las buenas practicas.
    El primer argumento al crear la instancia es
    el cuerpo de la respuesta, y el segundo argumento
    es el HTTP status.
HttpStatus:
    Es un Enum con los tipos de status para las respuestas
    y asi dar mayor precision a nuestras response.
 */
/*
ANOTACIONES DE SWAGGER:
@ApiOperation:
    Donde le indicas QUE HACE dicha request.
@ApiResponse:
    Le indicas que codigo devuelve y que mensaje.
@ApiResponses:
    Le indicas mas de una @ApiResponse.
@ApiParam:
    Le indicas que es lo que le debes enviar.,
    si es requerido y un ejemplo.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search a product width an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<Product> getProduct(
            @ApiParam(value = "The id of the product", required = true, example = "7")
            @PathVariable("id") int productId) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    @ApiOperation("Search a product by category id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<List<Product>> getByCategory(
            @ApiParam(value = "The id of the category", required = true, example = "7")
            @PathVariable("categoryId") int categoryId
    ) {
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @ApiOperation("Save a product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created")
    })
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete a product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity delete(
            @ApiParam(value = "The id of the product", required = true, example = "7")
            @PathVariable("id") int productId
    ) {
        if (productService.delete(productId)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
