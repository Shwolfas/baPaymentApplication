package com.bapayment.controllers;

import com.bapayment.api.BasePaymentAPI;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.mappers.PaymentMapper;
import com.bapayment.service.implementation.PaymentServiceImpl;
import com.bapayment.validations.PaymentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final PaymentServiceImpl paymentService;

    private final Map<String, PaymentMapper> paymentMappers;

    private final Map<String, PaymentValidation> paymentValidations;

    List<BasePaymentEntity> list;

    public TransactionController(PaymentServiceImpl paymentService,
                                 Map<String, PaymentMapper> paymentMappers,
                                 Map<String, PaymentValidation> paymentValdiations) {

        this.paymentService = paymentService;
        this.paymentMappers = paymentMappers;
        this.paymentValidations = paymentValdiations;
    }

    @PostMapping("/savePayment")
    public ResponseEntity<String> save(@RequestBody Map<String, Object> payload) {
        BasePaymentEntity payment;

        BasePaymentAPI paymentAPI;

        try {
            String type = (String) payload.get("type");
            if (type == null) {
                return ResponseEntity.badRequest().body("Payment type not found");
            }
            PaymentMapper mapper = paymentMappers.get(type+"Mapper");
            if (mapper == null) {
                return ResponseEntity.badRequest().body("Mapping failed");
            }

            paymentAPI = mapper.inputToAPI(payload);

            PaymentValidation validator = paymentValidations.get(type+"Validator");
            if (validator == null) {
                return ResponseEntity.badRequest().body("Validation failed");
            }
            validator.validate(paymentAPI);

            payment = mapper.apiToEntity(paymentAPI);

            paymentService.save(payment);
            list = paymentService.getAll();
            return ResponseEntity.ok("Payment saved successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }
}