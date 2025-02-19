package com.test.cts.services.impl;

import com.test.cts.constraint.Constraints;
import com.test.cts.dao.model.crypto.PriceAggregation;
import com.test.cts.dao.model.user.TransactionHistory;
import com.test.cts.dao.model.user.UserWallet;
import com.test.cts.dao.model.user.Users;
import com.test.cts.dao.repo.PriceAggregationRepository;
import com.test.cts.dao.repo.TransactionHistoryRepository;
import com.test.cts.dao.repo.UserRepository;
import com.test.cts.dao.repo.UserWalletRepository;
import com.test.cts.enums.OrderType;
import com.test.cts.enums.WhitelistCurrency;
import com.test.cts.exception.TradeException;
import com.test.cts.services.TradingService;
import com.test.cts.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TradingServiceImpl implements TradingService {

    Logger logger = LoggerFactory.getLogger(TradingServiceImpl.class);
    @Autowired
    private PriceAggregationRepository priceAggregationRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private UserWalletRepository userWalletRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Execute and complete the trade follow current best price
     * @param userId - User used for this trade
     * @param symbol - Which currency would like to trade
     * @param quantity - Quantity would like to trade
     * @param orderType - bid or ask for this trade
     */
    @Override
    public void executeTrade(Long userId, String symbol, Double quantity, String orderType){
        if(quantity <=0){
            logger.warn("Quantity should greater than 0 - {}", quantity);
            throw new TradeException(Constraints.INVALID_QUANTITY,"Quantity should greater than 0");
        }

        Users users  = userRepository.findEntityById(userId);

        if(users == null){
            logger.warn("User({}) Doesn't existed.", userId);
            throw new TradeException(Constraints.USER_NOT_EXIST,"User Doesn't existed.");
        }

        WhitelistCurrency whitelistCurrency = WhitelistCurrency.fromString(symbol);
        if(whitelistCurrency == null){
            logger.warn("Unavailable to trade this currency: {}", symbol);
            throw new TradeException(Constraints.CURRENCY_TRADE_NOT_AVAILABLE,"Unavailable to trade this currency: " + symbol);
        }


        PriceAggregation priceAggregation = priceAggregationRepository.findEntityBySymbol(symbol);

        if (priceAggregation == null) {
            logger.warn("No price data available for symbol: {}", symbol);
            throw new TradeException(Constraints.ERROR_CURRENCY_NOT_FOUND,"No price data available for symbol: " + symbol);
        }



        OrderType orderTypeEnum = OrderType.fromString(orderType);
        if(orderTypeEnum == null){
            logger.warn("Invalid order type (only support bid and ask): {}", orderType);
            throw new TradeException(Constraints.INVALID_ORDER_TYPE,"Invalid order type (only support bid and ask): " + orderType);
        }

        Double price = 0.0;
        if(orderTypeEnum == OrderType.BID){
            price = priceAggregation.getAsk();
            bid(userId,whitelistCurrency,quantity,price);
        }else if(orderTypeEnum == OrderType.ASK){
            price = priceAggregation.getBid();
            ask(userId,whitelistCurrency,quantity,price);
        }

        addTransactionHistory(userId,whitelistCurrency,orderTypeEnum,price,quantity);
    }

    private void bid(Long userId, WhitelistCurrency whitelistCurrency, Double quantity, Double targetPrice) throws TradeException {
        UserWallet sourceWallet = userWalletRepository.findEntityByUserIdAndSymbol(userId,whitelistCurrency.getCurrency2());
        UserWallet targetWallet = userWalletRepository.findEntityByUserIdAndSymbol(userId,whitelistCurrency.getCurrency1());

        if(sourceWallet == null){
            logger.warn("{} wallet not available", whitelistCurrency.getCurrency2());
            throw new TradeException(Constraints.WALLET_NOT_AVAILABLE,"USDT wallet not available");
        }

        Double totalPrice = targetPrice * quantity;
        if(sourceWallet.getBalance() < totalPrice){
            logger.warn("Trade failed due to not enough balance for user({})", sourceWallet.getUserId());
            throw new TradeException(Constraints.NOT_ENOUGH_BALANCE,"Trade failed due to not enough balance");
        }

        Double newSourceBalance = sourceWallet.getBalance() - totalPrice;

        if(targetWallet == null){
            targetWallet = new UserWallet(userId, whitelistCurrency.getCurrency1(), "","",0.0);
        }
        Double newTargetBalance = quantity + targetWallet.getBalance();
        logger.info("newSourceBalance: {}, newTargetBalance: {}", newSourceBalance, newTargetBalance);
        targetWallet.setBalance(CommonUtils.scaleTo8Decimal(newTargetBalance));

        sourceWallet.setBalance(CommonUtils.scaleTo8Decimal(newSourceBalance));
        updateWallet(sourceWallet,targetWallet);
    };

    private void ask(Long userId, WhitelistCurrency whitelistCurrency, Double quantity, Double targetPrice) throws TradeException {
        UserWallet sourceWallet = userWalletRepository.findEntityByUserIdAndSymbol(userId,whitelistCurrency.getCurrency1());
        UserWallet targetWallet = userWalletRepository.findEntityByUserIdAndSymbol(userId,whitelistCurrency.getCurrency2());

        if(sourceWallet == null){
            logger.warn("{} wallet not available", whitelistCurrency.getCurrency1());
            throw new TradeException(Constraints.WALLET_NOT_AVAILABLE, whitelistCurrency.getCurrency1()+" wallet not available");
        }

        if(sourceWallet.getBalance() < quantity){
            logger.warn("Trade failed due to not enough balance for user({})", sourceWallet.getUserId());
            throw new TradeException(Constraints.NOT_ENOUGH_BALANCE,"Trade failed due to not enough balance");
        }

        Double totalPrice = targetPrice * quantity;

        if(targetWallet == null){
            targetWallet = new UserWallet(userId, whitelistCurrency.getCurrency2(), "","",0.0);
        }

        Double newSourceBalance = sourceWallet.getBalance() - quantity;
        Double newTargetBalance = totalPrice + targetWallet.getBalance();
        logger.info("newSourceBalance: {}, newTargetBalance: {}", newSourceBalance, newTargetBalance);
        sourceWallet.setBalance(CommonUtils.scaleTo8Decimal(newSourceBalance));
        targetWallet.setBalance(CommonUtils.scaleTo8Decimal(newTargetBalance));
        updateWallet(sourceWallet,targetWallet);
    }

    private void updateWallet(UserWallet sourceWallet, UserWallet targetWallet){
        logger.info("Update wallet for user({})", sourceWallet.getUserId());
        sourceWallet.setUpdated_dttm(LocalDateTime.now());
        targetWallet.setUpdated_dttm(LocalDateTime.now());
        userWalletRepository.save(sourceWallet);
        userWalletRepository.save(targetWallet);
    }

    private void addTransactionHistory(Long userId,WhitelistCurrency whitelistCurrency,OrderType orderType, Double price, Double quantity){
        Double totalPrice = price * quantity;
        TransactionHistory transactionHistory = new TransactionHistory(userId,price,totalPrice,orderType.getType(), whitelistCurrency.getSymbol(), quantity);
        logger.info("Create new transaction history- {}", transactionHistory.toString());
        transactionHistoryRepository.save(transactionHistory);
    };

}
