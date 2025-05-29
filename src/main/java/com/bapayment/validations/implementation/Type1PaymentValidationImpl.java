package com.bapayment.validations.implementation;

import com.bapayment.api.BasePaymentAPI;
import com.bapayment.api.Type1PaymentAPI;
import com.bapayment.api.Type2PaymentAPI;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.validations.PaymentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component("TYPE1Validator")
public class Type1PaymentValidationImpl implements PaymentValidation {

    private final BasePaymentValidationImpl baseValidator;

    public Type1PaymentValidationImpl(BasePaymentValidationImpl baseValidator) {
        this.baseValidator = baseValidator;
    }

    public String getType() {
        return PaymentTypesEnum.TYPE1.toString();
    }

    @Override
    public void validate(BasePaymentAPI basePaymentAPI) {
        if (!(basePaymentAPI instanceof Type1PaymentAPI)) {
            throw new IllegalArgumentException("Expected Type2PaymentAPI");
        }

        Type1PaymentAPI type1PaymentAPI = (Type1PaymentAPI) basePaymentAPI;

        baseValidator.validateBase(type1PaymentAPI);

        if (type1PaymentAPI.getCurrency() != CurrencyEnum.EUR) {
            throw new IllegalArgumentException("TYPE1 payments are only applicable for EUR.");
        }

        String details = type1PaymentAPI.getDetails();
        if (details == null || details.isBlank()) {
            throw new IllegalArgumentException("details are missing or empty");
        }
    }
}
