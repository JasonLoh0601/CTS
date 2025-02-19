package com.test.cts.dao.model.crypto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "price_aggregation")
public class PriceAggregation {

    @Id
    @Column(name = "symbol")
    private String symbol;  // e.g., "BTCUSDT"

    @Column(name = "bid")
    private Double bid;  // Best price for selling

    @Column(name = "bid_from")
    private String bidFrom;  // Best bid price from Huobi or Binance

    @Column(name = "ask")
    private Double ask;  // Best price for buying

    @Column(name = "ask_from")
    private String askFrom;  // Best ask price from Huobi or Binance
    public PriceAggregation(){}

    public PriceAggregation(String symbol,
                            Double bid, String bidFrom,
                            Double ask , String askFrom) {
        this.symbol = symbol;
        this.bid = bid;
        this.bidFrom = bidFrom;
        this.ask = ask;
        this.askFrom = askFrom;
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

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getBidFrom() {
        return bidFrom;
    }

    public void setBidFrom(String bidFrom) {
        this.bidFrom = bidFrom;
    }

    public String getAskFrom() {
        return askFrom;
    }

    public void setAskFrom(String askFrom) {
        this.askFrom = askFrom;
    }
}
