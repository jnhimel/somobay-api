package com.himel.somobay.exceptions;

public class PhoneNumberAlreadyUsedException extends RuntimeException{
    public PhoneNumberAlreadyUsedException(String message) {
        super(message);
    }
}
