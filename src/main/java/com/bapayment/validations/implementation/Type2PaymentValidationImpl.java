package com.bapayment.validations.implementation;

import com.bapayment.api.BasePaymentAPI;
import com.bapayment.api.Type2PaymentAPI;
import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.validations.PaymentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component("TYPE2Validator")
public class Type2PaymentValidationImpl implements PaymentValidation {

    private final BasePaymentValidationImpl baseValidator;

    public Type2PaymentValidationImpl(BasePaymentValidationImpl baseValidator) {
        this.baseValidator = baseValidator;
    }
    @Override
    public String getType() {
        return PaymentTypesEnum.TYPE2.toString();
    }

    @Override
    public void validate(BasePaymentAPI basePaymentAPI) {
        if (!(basePaymentAPI instanceof Type2PaymentAPI)) {
            throw new IllegalArgumentException("Expected Type2PaymentAPI");
        }

        Type2PaymentAPI type2 = (Type2PaymentAPI) basePaymentAPI;

        baseValidator.validateBase(type2);

        if (type2.getCurrency() != CurrencyEnum.USD) {
            throw new IllegalArgumentException("TYPE2 payments are only applicable for USD.");
        }
    }

}
