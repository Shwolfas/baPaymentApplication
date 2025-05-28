package com.bapayment.service;

import com.bapayment.entities.BasePaymentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    void save(BasePaymentEntity payment);

    List<BasePaymentEntity> getAll();
}
