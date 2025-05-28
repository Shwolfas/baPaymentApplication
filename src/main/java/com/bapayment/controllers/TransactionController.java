package com.bapayment.controllers;

import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.mappers.PaymentMapper;
import com.bapayment.service.implementation.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final PaymentServiceImpl paymentService;

    private PaymentMapper type1PaymentMapper;
    private PaymentMapper type2PaymentMapper;
    private PaymentMapper type3PaymentMapper;

    public TransactionController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    @Qualifier("type1PaymentMapperImpl")
    public void setType1PaymentMapper(PaymentMapper type1PaymentMapper) {
        this.type1PaymentMapper = type1PaymentMapper;
    }

    @Autowired
    @Qualifier("type2PaymentMapperImpl")
    public void setType2PaymentMapper(PaymentMapper type2PaymentMapper) {
        this.type2PaymentMapper = type2PaymentMapper;
    }

    @Autowired
    @Qualifier("type3PaymentMapperImpl")
    public void setType3PaymentMapper(PaymentMapper type3PaymentMapper) {
        this.type3PaymentMapper = type3PaymentMapper;
    }

    @PostMapping("/savePayment")
    public ResponseEntity<String> save(@RequestBody Map<String, Object> payload) {
        try {
            String type = (String) payload.get("type");
            if (type == null) {
                return ResponseEntity.badRequest().body("Missing 'TYPE' in payload");
            }

            BasePaymentEntity payment;

            //TODO Optimise controller - spaghetti TYPE to entity conversion
            //TODO Add validation calls

            switch (type.toUpperCase()) {
                case "TYPE1":
                    payment = type1PaymentMapper.toEntity(payload);
                    break;
                case "TYPE2":
                    payment = type2PaymentMapper.toEntity(payload);
                    break;
                case "TYPE3":
                    payment = type3PaymentMapper.toEntity(payload);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Payment type not found: " + type);
            }

            paymentService.save(payment);
            return ResponseEntity.ok("Payment saved successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }
}