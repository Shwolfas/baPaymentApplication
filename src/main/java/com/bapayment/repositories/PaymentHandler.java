package com.bapayment.repositories;

import com.bapayment.entities.BasePayment;
import com.bapayment.entities.enums.PaymentTypesEnum;

public interface PaymentHandler {
    void save(BasePayment payment);
    PaymentTypesEnum getType();
}
