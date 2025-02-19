package com.test.cts.exception;

import com.test.cts.model.vo.ErrorMsgVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({TradeException.class})
    public ResponseEntity handleTradeException(TradeException e) {
        ErrorMsgVO vo = new ErrorMsgVO(e.getCode(),e.getMessage());
        return new ResponseEntity(vo, HttpStatus.BAD_REQUEST);
    }
}
