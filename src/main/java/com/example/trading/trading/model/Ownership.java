package com.example.trading.trading.model;

import javax.persistence.*;

@Entity
@Table(name = "ownership")
public class Ownership {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "cost_price")
    private Long costPrice;

    public Ownership() {
    }

    public Ownership(Long userId, Long stockId, Long quantity, Long costPrice) {
        this.userId = userId;
        this.stockId = stockId;
        this.quantity = quantity;
        this.costPrice = costPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Long costPrice) {
        this.costPrice = costPrice;
    }

}
