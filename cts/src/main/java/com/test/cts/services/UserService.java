package com.test.cts.services;

import com.test.cts.dao.model.user.TransactionHistory;
import com.test.cts.model.vo.user.InfoVO;

import java.util.List;

public interface UserService {

    InfoVO getInfo(Long id);
    List<TransactionHistory> tradeHistory(Long id);
}
