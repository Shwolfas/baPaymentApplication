package com.bapayment.service.implementation;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.mappers.PaymentMapper;
import com.bapayment.service.PaymentService;
import com.bapayment.service.TransactionService;
import com.bapayment.validations.PaymentValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class TransactionServiceImpl implements TransactionService {

    private final PaymentService paymentService;

    private final Map<String, PaymentMapper> paymentMappers;

    private final Map<String, PaymentValidation> paymentValidations;
    public TransactionServiceImpl(PaymentService paymentService, Map<String, PaymentMapper> paymentMappers, Map<String, PaymentValidation> paymentValidations) {
        this.paymentService = paymentService;
        this.paymentMappers = paymentMappers;
        this.paymentValidations = paymentValidations;
    }

    @Override
    public ResponseEntity<String> save(Map<String, Object> payload) {
        BasePaymentEntity payment;
        BasePaymentApi paymentAPI;

        try {
            String type = (String) payload.get("type");
            if (type == null) {
                return ResponseEntity.badRequest().body("Payment type not found");
            }
            PaymentMapper mapper = paymentMappers.get(type+"Mapper");
            if (mapper == null) {
                return ResponseEntity.badRequest().body("Mapping failed");
            }

            paymentAPI = mapper.inputToApi(payload);

            PaymentValidation validator = paymentValidations.get(type+"Validator");
            if (validator == null) {
                return ResponseEntity.badRequest().body("Validation failed");
            }
            validator.validate(paymentAPI);

            payment = mapper.apiToEntity(paymentAPI);

            paymentService.save(payment);
            return ResponseEntity.ok().body(type + " payment created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> cancel(Long id) {
        try {
            return paymentService.cancelPayment(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error: " + e.getMessage());
        }
    }
}
