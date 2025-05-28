package com.bapayment.validations.implementation;

import com.bapayment.enums.CurrencyEnum;
import com.bapayment.validations.PaymentValidation;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class Type3PaymentValidationImpl implements PaymentValidation {
    @Override
    public void validate(Map<String, Object> payload) {
        boolean isInvalidCurrency = Arrays.stream(CurrencyEnum.values())
                .noneMatch(c -> c.name().equals(payload.get("currency").toString()));

        if (isInvalidCurrency) {
            throw new IllegalArgumentException("Unknown payment type");
        }
        if (!payload.containsKey("creditor_bic") || !(payload.get("creditor_bic") instanceof String)) {
            throw new IllegalArgumentException("creditor_bic is missing or it is not text type");
        }
    }
}
