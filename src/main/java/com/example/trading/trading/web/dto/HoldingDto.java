package com.example.trading.trading.web.dto;

public class HoldingDto {
    private String stockName;
    private Long investedValue;
    private Long presentValue;
    private double diff;

    public HoldingDto() {

    }

    public HoldingDto(String stockName, Long investedValue, Long presentValue, double diff) {
        this.stockName = stockName;
        this.investedValue = investedValue;
        this.presentValue = presentValue;
        this.diff = diff;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Long getInvestedValue() {
        return investedValue;
    }

    public void setInvestedValue(Long investedValue) {
        this.investedValue = investedValue;
    }

    public Long getPresentValue() {
        return presentValue;
    }

    public void setPresentValue(Long presentValue) {
        this.presentValue = presentValue;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }
}
