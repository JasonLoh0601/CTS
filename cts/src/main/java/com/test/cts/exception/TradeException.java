package com.test.cts.exception;

public class TradeException extends RuntimeException{

    private int code;
    // Parameterless Constructor
    public TradeException() {}

    // Constructor that accepts a message
    public TradeException(int code,String message)
    {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
