package com.example.trading.trading.web.dto;

import java.time.LocalDateTime;

public class TransactionDto {
    private Long stockId;
    private String orderType;
    private Long quantity;
    private Long amount;

    public TransactionDto() {

    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public TransactionDto(Long stockId, String orderType, Long quantity, Long amount) {
        this.stockId = stockId;
        this.orderType = orderType;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
