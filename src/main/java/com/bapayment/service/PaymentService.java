package com.bapayment.service;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.entities.BasePaymentEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    void save(BasePaymentEntity payment);

    ResponseEntity<String> cancelPayment(Long id);
    List<BasePaymentEntity> getAll();
}
