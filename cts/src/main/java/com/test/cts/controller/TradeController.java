package com.test.cts.controller;

import com.test.cts.exception.TradeException;
import com.test.cts.model.vo.ErrorMsgVO;
import com.test.cts.model.vo.TradeVO;
import com.test.cts.services.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    TradingService tradingService;

    @PostMapping("/trade")
    public ResponseEntity executeTrade(@RequestBody TradeVO tradeVO) {
        tradingService.executeTrade(tradeVO.getUserId(), tradeVO.getSymbol()
                , tradeVO.getQuantity(), tradeVO.getOrderType());
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
