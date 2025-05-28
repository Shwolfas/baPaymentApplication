package com.bapayment.validations.implementation;

import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.validations.PaymentValidation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class Type1PaymentValidationImpl implements PaymentValidation {
    @Override
    public void validate(Map<String, Object> payload) {
        if (Objects.equals(payload.get("currency"), CurrencyEnum.EUR)) {
            throw new IllegalArgumentException("TYPE1 payments are only applicable for EUR.");
        }
        if (!payload.containsKey("details") || !(payload.get("details") instanceof String) || ((String) payload.get("details")).isEmpty()) {
            throw new IllegalArgumentException("details are missing or it is not text type");
        }
    }
}
