package com.gdrc.market.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "purchases")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Integer purchaseId;

    @Column(name = "customer_id")
    private String customerId;

    private LocalDateTime date;

    @Column(name = "payment_method")
    private String paymentMethod;

    private String comment;

    private String state;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "productEntity")
    private List<PurchasesProductEntity> products;

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
