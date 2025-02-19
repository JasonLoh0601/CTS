package com.test.cts.model.vo.user;

public class WalletVO {
    private String symbol;
    private String balance;

    public WalletVO(String symbol, String balance) {
        this.symbol = symbol;
        this.balance = balance;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
