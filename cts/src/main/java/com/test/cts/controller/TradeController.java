package com.test.cts.controller;

import com.test.cts.model.vo.TradeRequestVO;
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
    public ResponseEntity executeTrade(@RequestBody TradeRequestVO tradeRequestVO) {
        tradingService.executeTrade(tradeRequestVO.getUserId(), tradeRequestVO.getSymbol()
                , tradeRequestVO.getQuantity(), tradeRequestVO.getOrderType());
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
