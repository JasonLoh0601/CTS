package com.test.cts.model.vo;


public class TradeRequestVO {
    private Long userId;
    private String symbol;
    private Double quantity;
    private String orderType;


    public TradeRequestVO(Long userId, String symbol, Double quantity, String orderType) {
        this.userId = userId;
        this.symbol = symbol;
        this.quantity = quantity;
        this.orderType = orderType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
