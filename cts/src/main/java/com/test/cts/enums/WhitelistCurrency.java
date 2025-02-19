package com.test.cts.enums;

public enum WhitelistCurrency {
    BTCUSDT("BTCUSDT","BTC","USDT"), ETHUSDT("ETHUSDT","ETH","USDT");

    private String symbol;
    private String currency1;
    private String currency2;

    WhitelistCurrency(String symbol,String currency1,String currency2) {
        this.symbol = symbol;
        this.currency1 = currency1;
        this.currency2 = currency2;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCurrency1() {
        return currency1;
    }

    public String getCurrency2() {
        return currency2;
    }

    public static WhitelistCurrency fromString(String symbol) {
        for (WhitelistCurrency b : WhitelistCurrency.values()) {
            if (b.symbol.equalsIgnoreCase(symbol)) {
                return b;
            }
        }
        return null;
    }
}
