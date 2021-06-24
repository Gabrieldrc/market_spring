package com.gdrc.market.persistence.mapper;

import com.gdrc.market.domain.Product;
import com.gdrc.market.persistence.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/*
Cuando el target este esperando una instancia de Dominio,
Debemos indicarle, si ya tiene un mapper, que debe usar el mapper
de esa clase para mapearlo. (Como en este caso con category.

En el segundo metodo ("toProducts") no debemos indicarle el
mapeo ya que el usara para cada productEntity el mismo mapeo
anterior.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "sellPrice", target = "price"),
            @Mapping(source = "stockQuantity", target = "stock"),
            @Mapping(source = "state", target = "active"),
            @Mapping(source = "categoryEntity", target = "category"),
    })
    Product toProduct(ProductEntity product);

    List<Product> toProducts(List<ProductEntity> products);

    @InheritInverseConfiguration
    @Mapping(target = "barCode", ignore = true)
    ProductEntity toProductEntity(Product product);
}
