package com.test.cts.dao.model.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_history")
public class TransactionHistory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)  // Auto-generate the UUID for the user
    private Long id;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "price")
    private Double price;

    @Column(name = "type")
    private String type;

    @Column(name = "symbol")
    private String symbol;
    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "created_dttm")
    private LocalDateTime created_dttm;

    public TransactionHistory(){}

    public TransactionHistory(Long userId,Double price,Double totalPrice, String type,
                              String symbol, Double quantity) {
        this.userId = userId;
        this.price = price;
        this.totalPrice =totalPrice;
        this.type = type;
        this.symbol = symbol;
        this.quantity =quantity;
        setCreated_dttm(LocalDateTime.now());
    }
    public TransactionHistory(Long id,Long userId, Double price,Double totalPrice, String type,
                              String symbol, Double quantity) {
        this.userId = userId;
        this.id = id;
        this.price = price;
        this.type = type;
        this.symbol = symbol;
        this.quantity =quantity;
        setCreated_dttm(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "id=" + id +
                ", userId=" + userId +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", symbol='" + symbol + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public LocalDateTime getCreated_dttm() {
        return created_dttm;
    }

    public void setCreated_dttm(LocalDateTime created_dttm) {
        this.created_dttm = created_dttm;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
