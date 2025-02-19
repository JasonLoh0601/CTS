package com.test.cts.model.vo.user;

import java.util.List;

public class InfoVO {
    private String username;
    private String email;
    private String phoneNumber;
    private List<WalletVO> wallets;


    public InfoVO( String username, String email, String phoneNumber, List<WalletVO> wallets) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.wallets = wallets;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<WalletVO> getWallets() {
        return wallets;
    }

    public void setWallets(List<WalletVO> wallets) {
        this.wallets = wallets;
    }
}
