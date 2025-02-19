package com.test.cts.model.vo;

public class PriceVO {
    private String symbol;
    private String bid;
    private String ask;

    public PriceVO(String symbol, String bid, String ask) {
        this.symbol = symbol;
        this.bid = bid;
        this.ask = ask;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }
}
