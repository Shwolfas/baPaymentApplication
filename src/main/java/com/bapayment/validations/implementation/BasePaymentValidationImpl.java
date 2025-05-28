package com.bapayment.validations.implementation;

import com.bapayment.validations.PaymentValidation;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BasePaymentValidationImpl implements PaymentValidation {
    @Override
    public void validate(Map<String, Object> payload) {
        if (!payload.containsKey("amount") || !(payload.get("amount") instanceof Number) || ((Number) payload.get("amount")).longValue() < 0) {
            throw new IllegalArgumentException("amount is missing or it is not a positive number");
        }
        if (!payload.containsKey("currency") || !(payload.get("currency") instanceof String)) {
            throw new IllegalArgumentException("currency is missing or it is not text type");
        }
        if (!payload.containsKey("debtor_iban") || !(payload.get("debtor_iban") instanceof String)) {
            throw new IllegalArgumentException("debtor_iban is missing or it is not text type");
        }
        if (!payload.containsKey("creditor_iban") || !(payload.get("creditor_iban") instanceof String)) {
            throw new IllegalArgumentException("creditor_iban is missing or it is not text type");
        }
    }
}
