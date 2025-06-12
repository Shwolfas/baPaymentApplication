package com.bapayment.validations.implementation;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.api.Type3PaymentApi;
import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.validations.BasePaymentValidation;
import com.bapayment.validations.PaymentValidation;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("TYPE3Validator")
public class Type3PaymentValidationImpl implements PaymentValidation {

    private final BasePaymentValidation baseValidator;

    public Type3PaymentValidationImpl(BasePaymentValidation baseValidator) {
        this.baseValidator = baseValidator;
    }

    @Override
    public String getType() {
        return PaymentTypesEnum.TYPE3.toString();
    }

    @Override
    public void validate(BasePaymentApi basePaymentAPI) {
        if (!(basePaymentAPI instanceof Type3PaymentApi)) {
            throw new IllegalArgumentException("Expected Type3PaymentAPI");
        }

        Type3PaymentApi type3 = (Type3PaymentApi) basePaymentAPI;

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
