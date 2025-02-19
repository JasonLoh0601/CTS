package com.test.cts.services.impl;

import com.test.cts.constraint.Constraints;
import com.test.cts.dao.model.user.TransactionHistory;
import com.test.cts.dao.model.user.UserWallet;
import com.test.cts.dao.model.user.Users;
import com.test.cts.dao.repo.TransactionHistoryRepository;
import com.test.cts.dao.repo.UserRepository;
import com.test.cts.dao.repo.UserWalletRepository;
import com.test.cts.exception.TradeException;
import com.test.cts.model.vo.user.InfoVO;
import com.test.cts.model.vo.user.WalletVO;
import com.test.cts.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserWalletRepository userWalletRepository;

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    /**
     * Retrieve User info include wallet details
     * @param id - User id
     * @return
     */
    @Override
    public InfoVO getInfo(Long id) {
        logger.info("Retrieve user information started.");
        Users u = userRepository.findEntityById(id);
        if (u == null) {
            throw new TradeException(Constraints.USER_NOT_EXIST,"User Doesn't existed.");
        }

        String username, email, phoneNumber;
        username = u.getUsername();
        email = u.getEmail();
        phoneNumber = u.getPhoneNumber();
        List<UserWallet> userWallets = userWalletRepository.findEntityByUserId(id);
        List<WalletVO> wallets = new ArrayList<>();
        for (UserWallet userWallet : userWallets) {
            WalletVO walletVO = new WalletVO(userWallet.getSymbol(),String.format("%.8f",userWallet.getBalance()));
            wallets.add(walletVO);
        }

        logger.info("Retrieve user information ended.");
        return new InfoVO(username, email, phoneNumber, wallets);
    }

    /**
     * Retrieve transaction history for the user
     * @param userId - User id used for retrieve transaction history
     * @return
     */
    @Override
    public List<TransactionHistory> tradeHistory(Long userId) {
        logger.info("Retrieve user trade transaction started.");
        List<TransactionHistory> histories = transactionHistoryRepository.findAllByUserId(userId);
        if(histories == null){
            return new ArrayList<>();
        }
        logger.info("Retrieve user trade transaction ended.");
        return histories;
    }


}
