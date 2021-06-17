package com.gdrc.market.persistence.crud;

import com.gdrc.market.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

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
/*
Uso de Query Methods
    Cuando necesitamos hacer consultas que Spring Data no puede ofrecer.
    Proveen la posibilidad de generar consultas mediante el nombre
    de los metodos.
    Tambien tiene la posibilidad de retornar Optional<T>
    mas info:
    https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

    Como ejemplo en la interfaz creamos un metodo donde colocamos:
    findBy: representa el "WHERE"
    CategoryId: la propiedad que representa la columna de la tabla
    (hay que escribirlo tal cual, solo cambiando las mayusculas para
    cumplir con el cammel case)
    Y por parametro tiene que llamarse exactamente como la propiedad.

    NOTA: Tambien podemos llamar como queramos al metodo y usar la
    anotacion @Query con el valor que representa el query.

 */
public interface ProductCrudRepository extends CrudRepository<ProductEntity, Integer> {
    //    @Query(value = "SELECT * FROM products WHERE category_id = ? ORDER BY ASC", nativeQuery = true)
    List<ProductEntity> findByCategoryIdOrderByNameAsc(int categoryId);

    Optional<List<ProductEntity>> findByStockQuantityLessThanAndState(int stockQuantity, boolean state);
}
