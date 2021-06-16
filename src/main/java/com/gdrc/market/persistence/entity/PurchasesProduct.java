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
 */

@Entity
@Table(name = "purchases_products")
public class PurchasesProduct {
    @EmbeddedId
    private PurchasesProductPK id;

    private Integer quantity;

    private Double total;

    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "purchase_id", insertable = false, updatable = false)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

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
}
