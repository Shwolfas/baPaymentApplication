package com.bapayment.exceptions;

public class PaymentAlreadyCanceledException extends Exception {
    public PaymentAlreadyCanceledException(String message) {
        super(message);
    }
}
