package com.example.trading.trading.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private Long wallet;

    @Column(name = "invested_value")
    private Long investedValue;

    @Column(name = "name")
    private String name;

    private String email;

    private String password;

    private String role;

    public User() {

    }

    public User(String name, String email, String password, String role, Long investedValue, Long wallet) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.investedValue = investedValue;
        this.wallet = wallet;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Long getWallet() {
        return wallet;
    }

    public void setWallet(Long wallet) {
        this.wallet = wallet;
    }

    public Long getInvestedValue() {
        return investedValue;
    }

    public void setInvestedValue(Long investedValue) {
        this.investedValue = investedValue;
    }

}
