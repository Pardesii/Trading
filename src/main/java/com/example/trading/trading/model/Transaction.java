package com.example.trading.trading.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "type")
    private String type;

    @Column(name = "cost_price")
    private Long costPrice;

    @Column(name = "date")
    LocalDateTime localDateTime;

    public Transaction() {

    }

    public Transaction(Long userId, String stockId, Long quantity, Long costPrice, LocalDateTime localDateTime, String type) {
        this.userId = userId;
        this.stockName = stockId;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.localDateTime = localDateTime;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
