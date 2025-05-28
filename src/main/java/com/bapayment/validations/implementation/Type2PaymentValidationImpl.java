package com.bapayment.validations.implementation;

import com.bapayment.enums.CurrencyEnum;
import com.bapayment.validations.PaymentValidation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class Type2PaymentValidationImpl implements PaymentValidation {
    @Override
    public void validate(Map<String, Object> payload) {
        if (Objects.equals(payload.get("currency"), CurrencyEnum.USD)) {
            throw new IllegalArgumentException("TYPE1 payments are only applicable for USD.");
        }
    }
}
