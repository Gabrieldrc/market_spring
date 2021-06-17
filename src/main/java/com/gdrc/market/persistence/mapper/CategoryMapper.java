package com.gdrc.market.persistence.mapper;

import com.gdrc.market.domain.Category;
import com.gdrc.market.persistence.entity.CategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/*
@Mapper:
    Es la anotacion para indicarle que es un mapeador. Y con el
    componentModel le indicamos que es un componente de "spring"
@Mappings:
    Es la anotacion para agregar mas de una anotacion dentro de
    llaves
@Mapping:
    Es la anotacion donde le indicamos cual es el equivalente
    que espera recibir la clase target de la clase entity.
    NOTA: Las propiedades que tengan el mismo nombre no se
    colocan porque esas se mapea de manera automatica.
@InheritInverseConfiguration:
    Es la anotacion donde le indicamos que la configuracion de
    mapeo sera la misma que la que ya definimos pero inversa.
ignore = true
    Con esto le indicamos que esa propiedad no debe mapearla.
 */

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "description", target = "category"),
            @Mapping(source = "state", target = "active"),
    })
    Category toCategory(CategoryEntity category);

    @InheritInverseConfiguration
    @Mapping(target = "productEntities", ignore = true)
    CategoryEntity toCategoryEntity(Category category);

}
