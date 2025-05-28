package com.bapayment.validations;

import com.bapayment.entities.BasePaymentEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface PaymentValidation {
    void validate(Map<String, Object> payload);
}
