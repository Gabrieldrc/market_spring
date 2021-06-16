package com.gdrc.market.persistence.crud;

import com.gdrc.market.persistence.entity.Product;
import org.springframework.data.repository.CrudRepository;

/*
Spring Data Repositories:
    Te ayuda a ahorrarte codigo y puedes hacer operaciones
    sin codigo en la DB.
    Los repositorios de Spring Data son:
    CrudRepository:
        Que nos permiten hacer operaciones CRUD
    PagingAndSortingRepository:
        Igual que el anterior, pero tambien nos permite
        realizar operaciones de paginacion y ordenamiento.
    JPARepository:
        Igual que las DOS anteriores, pero ademas nos
        permite hacer tareas de JPA especificas (como flush)
 */
/*
    Este CrudRepository debe extender de CrudRepository,
    indicandole la tabla y el tipo de la pk.
 */
public interface ProductCrudRepository extends CrudRepository<Product, Integer> {
}
