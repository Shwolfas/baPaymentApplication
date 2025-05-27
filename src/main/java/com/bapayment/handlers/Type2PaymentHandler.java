package com.bapayment.handlers;

import com.bapayment.entities.BasePayment;
import com.bapayment.entities.enums.PaymentTypesEnum;
import com.bapayment.repositories.PaymentHandler;
import com.bapayment.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class Type2PaymentHandler implements PaymentHandler {
    private final PaymentRepository paymentRepository;

    public Type2PaymentHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void save(BasePayment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public PaymentTypesEnum getType() {
        return PaymentTypesEnum.TYPE2;
    }
}
