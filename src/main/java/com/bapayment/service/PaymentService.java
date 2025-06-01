package com.bapayment.service;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.api.query.CancelFeeView;
import com.bapayment.entities.BasePaymentEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface PaymentService {
    void save(BasePaymentEntity payment);

    ResponseEntity<String> cancelPayment(Long id) throws Exception;
    List<BasePaymentEntity> getAll();

    Optional<CancelFeeView> getPaymentById(Long id);

    List<Long> getAllValid();

    List<Long> getAllValidByAmount(Double amount);
}
