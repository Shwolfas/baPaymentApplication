package com.bapayment.validations.implementation;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.entities.BasePaymentEntity;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BasePaymentValidationImpl {

    protected void validateBase(BasePaymentApi basePaymentAPI) {
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

    public void validateBaseCancelation(BasePaymentEntity basePaymentEntity) {
        if (basePaymentEntity.isCanceled()) {
            throw new IllegalStateException("Payment #" + basePaymentEntity.getId() + " is already cancelled");
        }
        if (!basePaymentEntity.getInsert_date().toLocalDate().equals(LocalDate.now())){
            throw new IllegalStateException("Unable to cancel #" + basePaymentEntity.getId() + ". Only same day payments can be canceled");
        }
    }
}
