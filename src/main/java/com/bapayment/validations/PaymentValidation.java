package com.bapayment.validations;

import com.bapayment.api.BasePaymentApi;
import org.springframework.stereotype.Component;

@Component
public interface PaymentValidation {
    void validate(BasePaymentApi basePaymentAPI);
    String getType();
}
