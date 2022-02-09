package com.example.trading.trading.web.dto;

public class WalletDto {
    private long amount;

    public WalletDto() {

    }

    public WalletDto(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
