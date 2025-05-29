package com.bapayment.validations.implementation;

import com.bapayment.api.BasePaymentAPI;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BasePaymentValidationImpl {

    protected void validateBase(BasePaymentAPI basePaymentAPI) {
        if (basePaymentAPI.getAmount() < 0) {
            throw new IllegalArgumentException("amount must be a positive number");
        }
        if (basePaymentAPI.getCurrency() == null) {
            throw new IllegalArgumentException("currency is missing");
        }
        if (basePaymentAPI.getDebtor_iban() == null || basePaymentAPI.getDebtor_iban().isBlank()) {
            throw new IllegalArgumentException("debtor_iban is missing or blank");
        }
        if (basePaymentAPI.getCreditor_iban() == null || basePaymentAPI.getCreditor_iban().isBlank()) {
            throw new IllegalArgumentException("creditor_iban is missing or blank");
        }
        if (basePaymentAPI.getType() == null) {
            throw new IllegalArgumentException("payment type is missing");
        }
    }
}
