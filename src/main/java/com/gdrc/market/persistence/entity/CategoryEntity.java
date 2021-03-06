package com.gdrc.market.persistence.entity;

import javax.persistence.*;
import java.util.List;

/*
@OneToMany:
    Esta anotacion indicamos la relacion que tiene,
    en este caso, Category con Product, que es
    Uno a Muchos (lo contrario a lo que colocamos
    en Product), y le indicamos por que propiedad
    esta mapeada que seria la propiedad category
    que tenemos en Product.
 */
@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    private String description;

    private Boolean state;

    @OneToMany(mappedBy = "categoryEntity")
    private List<ProductEntity> productEntities;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }
}
