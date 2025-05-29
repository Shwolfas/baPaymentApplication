package com.bapayment.validations.implementation;

import com.bapayment.api.BasePaymentAPI;
import com.bapayment.api.Type3PaymentAPI;
import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.validations.PaymentValidation;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Component("TYPE3Validator")
public class Type3PaymentValidationImpl implements PaymentValidation {

    private final BasePaymentValidationImpl baseValidator;

    public Type3PaymentValidationImpl(BasePaymentValidationImpl baseValidator) {
        this.baseValidator = baseValidator;
    }

    @Override
    public String getType() {
        return PaymentTypesEnum.TYPE3.toString();
    }

    @Override
    public void validate(BasePaymentAPI basePaymentAPI) {
        if (!(basePaymentAPI instanceof Type3PaymentAPI)) {
            throw new IllegalArgumentException("Expected Type3PaymentAPI");
        }

        Type3PaymentAPI type3 = (Type3PaymentAPI) basePaymentAPI;

        baseValidator.validateBase(type3);

        CurrencyEnum currency = type3.getCurrency();
        boolean isInvalidCurrency = Arrays.stream(CurrencyEnum.values())
                .noneMatch(c -> c == currency);

        if (isInvalidCurrency) {
            throw new IllegalArgumentException("Unknown payment type");
        }

        String creditorBic = type3.getCreditor_bic();
        if (creditorBic == null || creditorBic.isBlank()) {
            throw new IllegalArgumentException("creditor_bic is missing");
        }
    }
}
