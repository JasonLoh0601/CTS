package com.test.cts.dao.model.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_wallet")
public class UserWallet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)  // Auto-generate the UUID for the user
    private Long id;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "public_key")
    private String publicKey;

    @Column(name = "private_key")
    private String privateKey;  // Securely stored and never exposed

    @Column(name = "balance")
    private Double balance;

    @Column(name = "updated_dttm")
    private LocalDateTime updated_dttm;

    public UserWallet(){}

    public UserWallet(Long userId,  String symbol, String publicKey, String privateKey, Double initialBalance) {
        this.userId = userId;
        this.symbol = symbol;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.balance = initialBalance;
        setUpdated_dttm(LocalDateTime.now());
    }

    public UserWallet(Long id,Long userId,  String symbol, String publicKey, String privateKey, Double initialBalance) {
        this.id = id;
        this.userId = userId;
        this.symbol = symbol;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.balance = initialBalance;
        setUpdated_dttm(LocalDateTime.now());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getUpdated_dttm() {
        return updated_dttm;
    }

    public void setUpdated_dttm(LocalDateTime updated_dttm) {
        this.updated_dttm = updated_dttm;
    }
}
