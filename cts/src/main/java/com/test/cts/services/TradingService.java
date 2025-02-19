package com.test.cts.services;


import com.test.cts.exception.TradeException;

public interface TradingService {

    void executeTrade(Long userId, String symbol, Double quantity, String orderType);
}
