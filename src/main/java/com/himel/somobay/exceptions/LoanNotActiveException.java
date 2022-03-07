package com.himel.somobay.exceptions;

public class LoanNotActiveException extends RuntimeException{
    public LoanNotActiveException(String message) {
        super(message);
    }
}
