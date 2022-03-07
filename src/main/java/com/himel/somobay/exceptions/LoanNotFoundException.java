package com.himel.somobay.exceptions;

public class LoanNotFoundException extends RuntimeException{
    public LoanNotFoundException(String message) {
        super(message);
    }
}
