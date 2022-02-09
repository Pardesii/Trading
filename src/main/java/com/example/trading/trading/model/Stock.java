package com.example.trading.trading.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "aum")
    private Long aum;

    @Column(name = "invested_users")
    private Long investedUsers;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "prev_price")
    private Long prevPrice;

    @Column(name = "cur_price")
    private Long curPrice;

    @Column(name = "diff")
    private Long diff;

    @Column(name = "start_value")
    private Long startValue;

    @Column(name = "end_value")
    private Long endValue;

    @Column(name = "descr", nullable = false)
    private String desc;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "director")
    private String director;


    public Stock() {

    }

    public Stock(String name, String symbol, Long aum, Long investedUsers, String image, Long prevPrice, Long curPrice, Long diff, Long startValue, Long endValue, String desc, String category, String director) {
        this.name = name;
        this.symbol = symbol;
        this.aum = aum;
        this.investedUsers = investedUsers;
        this.image = image;
        this.prevPrice = prevPrice;
        this.curPrice = curPrice;
        this.diff = diff;
        this.startValue = startValue;
        this.endValue = endValue;
        this.desc = desc;
        this.category = category;
        this.director = director;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getAum() {
        return aum;
    }

    public void setAum(Long aum) {
        this.aum = aum;
    }

    public Long getInvestedUsers() {
        return investedUsers;
    }

    public void setInvestedUsers(Long investedUsers) {
        this.investedUsers = investedUsers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrevPrice() {
        return prevPrice;
    }

    public void setPrevPrice(Long prevPrice) {
        this.prevPrice = prevPrice;
    }

    public Long getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(Long curPrice) {
        this.curPrice = curPrice;
    }

    public Long getDiff() {
        return diff;
    }

    public void setDiff(Long diff) {
        this.diff = diff;
    }

    public Long getStartValue() {
        return startValue;
    }

    public void setStartValue(Long startValue) {
        this.startValue = startValue;
    }

    public Long getEndValue() {
        return endValue;
    }

    public void setEndValue(Long endValue) {
        this.endValue = endValue;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
