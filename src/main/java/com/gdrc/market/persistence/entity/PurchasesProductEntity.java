package com.gdrc.market.persistence.entity;

import javax.persistence.*;

/*
Cuando nuestra tabla tiene dos primary key
entonces se crea una clase con el mismo nombre
que esta pero finalizando con PK.
Luego se usa la entidad:
@EmbeddedId:
    Esta indica que la clave primaria es compuesta
    y esta dada por otra clase.
@MapsId:
    Es la anotacion que le indica el nombre de
    la primary key que queremos que se enlace.
    Asi cuando, en este caso, PurchasesProductEntity
    se guarde en cascada, se vera a que primary key
    pertenece cada uno de los productos que esta
    en una compra
 */

@Entity
@Table(name = "purchases_products")
public class PurchasesProductEntity {
    @EmbeddedId
    private PurchasesProductPK id;

    private Integer quantity;

    private Double total;

    private Boolean state;

    @ManyToOne
    @MapsId("purchaseId")
    @JoinColumn(name = "purchase_id", insertable = false, updatable = false)
    private PurchaseEntity purchaseEntity;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductEntity productEntity;

    public PurchasesProductPK getId() {
        return id;
    }

    public void setId(PurchasesProductPK id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public PurchaseEntity getPurchaseEntity() {
        return purchaseEntity;
    }

    public void setPurchaseEntity(PurchaseEntity purchaseEntity) {
        this.purchaseEntity = purchaseEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
