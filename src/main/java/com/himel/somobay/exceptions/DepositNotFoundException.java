package com.himel.somobay.exceptions;

public class DepositNotFoundException extends RuntimeException{
    public DepositNotFoundException(String message) {
        super(message);
    }
}
