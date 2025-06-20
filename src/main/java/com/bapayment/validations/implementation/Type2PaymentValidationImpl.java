package com.bapayment.validations.implementation;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.api.Type2PaymentApi;
import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.validations.BasePaymentValidation;
import com.bapayment.validations.PaymentValidation;
import org.springframework.stereotype.Component;

@Component("TYPE2Validator")
public class Type2PaymentValidationImpl implements PaymentValidation {

    private final BasePaymentValidation baseValidator;

    public Type2PaymentValidationImpl(BasePaymentValidation baseValidator) {
        this.baseValidator = baseValidator;
    }
    @Override
    public String getType() {
        return PaymentTypesEnum.TYPE2.toString();
    }

    @Override
    public void validate(BasePaymentApi basePaymentAPI) {
        if (!(basePaymentAPI instanceof Type2PaymentApi)) {
            throw new IllegalArgumentException("Expected Type2PaymentAPI");
        }

        Type2PaymentApi type2 = (Type2PaymentApi) basePaymentAPI;

        baseValidator.validateBase(type2);

        if (type2.getCurrency() != CurrencyEnum.USD) {
            throw new IllegalArgumentException("TYPE2 payments are only applicable for USD.");
        }
    }

}
