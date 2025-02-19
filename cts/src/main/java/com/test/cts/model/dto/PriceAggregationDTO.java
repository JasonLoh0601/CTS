package com.test.cts.model.dto;

public class PriceAggregationDTO {
    private String symbol;
    private Double bid;
    private String bidSource;
    private Double ask;
    private String askSource;

    public PriceAggregationDTO() {
    }

    public PriceAggregationDTO(String symbol, Double bid, Double ask) {
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

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public void compareBid(Double bid, String source) {
        if (this.bid == null || bid >= this.bid) {
            setBid(bid);
            setBidSource(source);
        }
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public void compareAsk(Double ask, String source) {
        if (this.ask == null || ask >= this.ask) {
            setAsk(ask);
            setAskSource(source);
        }
    }

    public String getBidSource() {
        return bidSource;
    }

    public void setBidSource(String bidSource) {
        this.bidSource = bidSource;
    }

    public String getAskSource() {
        return askSource;
    }

    public void setAskSource(String askSource) {
        this.askSource = askSource;
    }
}
