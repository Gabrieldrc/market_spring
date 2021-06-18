package com.gdrc.market.domain;

import com.gdrc.market.persistence.entity.ProductEntity;
import com.gdrc.market.persistence.entity.PurchaseEntity;

public class PurchaseItem {
    private int id;
    private int quantity;
    private double total;
    private boolean state;
//    private PurchaseEntity purchaseEntity;
//    private ProductEntity productEntity;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
