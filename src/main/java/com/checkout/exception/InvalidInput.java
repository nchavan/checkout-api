package com.checkout.exception;

public class InvalidInput extends Exception {
    public InvalidInput(final String errorMessage) {
        super(errorMessage);
    }
}
