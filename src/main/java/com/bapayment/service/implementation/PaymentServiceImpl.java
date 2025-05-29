package com.bapayment.service.implementation;

import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.repositories.BasePaymentRepository;
import com.bapayment.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final BasePaymentRepository basePaymentRepository;

    public PaymentServiceImpl(BasePaymentRepository basePaymentRepository) {
        this.basePaymentRepository = basePaymentRepository;
    }

    @Override
    public void save(BasePaymentEntity payment) {
        basePaymentRepository.save(payment);
    }

    public List<BasePaymentEntity> getAll() {
        return basePaymentRepository.findAll();
    }
}
