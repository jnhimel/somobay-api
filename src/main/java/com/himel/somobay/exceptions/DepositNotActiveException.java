package com.himel.somobay.exceptions;

public class DepositNotActiveException extends RuntimeException{
    public DepositNotActiveException(String message) {
        super(message);
    }
}
