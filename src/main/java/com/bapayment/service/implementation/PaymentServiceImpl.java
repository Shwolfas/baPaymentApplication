package com.bapayment.service.implementation;

import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.repositories.PaymentRepository;
import com.bapayment.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void save(BasePaymentEntity payment) {
        paymentRepository.save(payment);
    }

    public List<BasePaymentEntity> getAll() {
        return paymentRepository.findAll();
    }
}
