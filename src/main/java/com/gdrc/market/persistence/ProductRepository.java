package com.gdrc.market.persistence;

import com.gdrc.market.domain.Product;
import com.gdrc.market.domain.repository.IProductRepository;
import com.gdrc.market.persistence.crud.ProductCrudRepository;
import com.gdrc.market.persistence.entity.ProductEntity;
import com.gdrc.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
@Repository:
    Con esta anotacion le indicamos a Spring que esta clase se
    encargara de interactuar directamente con la db.
@Autowired:
    Con esta anotacion aplicamos el principio IoC (Inverse of Control),
    para indicarle a Spring que sea él el que se encargue de inyectar
    y crear las dependencias.
    NOTA: Asegurarse que sean componentes de Spring, de otro modo NO
    podra realizar la inyeccion.
    (En el caso de ProductCrudRepository el no es un componente de
    Spring, pero extiende de CrudRepository que SI lo es. y el
    mapper le indico a travez del componente de Mapper que el es
    un componente de Spring aca 'componentModel = "spring"').
 */
@Repository
public class ProductRepository implements IProductRepository {
    @Autowired
    private ProductCrudRepository productCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<ProductEntity> productEntities = (List<ProductEntity>) productCrudRepository.findAll();
        return mapper.toProducts(productEntities);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<ProductEntity> productEntities = productCrudRepository.findByCategoryIdOrderByNameAsc(categoryId);
        return Optional.of(mapper.toProducts(productEntities));
    }

    @Override
    public Optional<List<Product>> getByQuantityLessThan(int stockQuantity) {
        Optional<List<ProductEntity>> productEntities = productCrudRepository.findByStockQuantityLessThanAndState(stockQuantity, true);
        return productEntities.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productCrudRepository.findById(productId)
                .map(prod -> mapper.toProduct(prod));
    }

    @Override
    public Product save(Product product) {
        return mapper.toProduct(productCrudRepository.save(mapper.toProductEntity(product)));
    }

    @Override
    public void delete(int productId) {
        productCrudRepository.deleteById(productId);
    }
}
