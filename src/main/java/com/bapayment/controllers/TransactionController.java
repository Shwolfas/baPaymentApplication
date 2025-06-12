package com.bapayment.controllers;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.mappers.PaymentMapper;
import com.bapayment.service.PaymentService;
import com.bapayment.service.TransactionService;
import com.bapayment.validations.PaymentValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/newPayment")
    public ResponseEntity<String> save(@RequestBody Map<String, Object> payload) {
        return transactionService.save(payload);
    }

    @PatchMapping("/cancelPayment/{id}")
    public ResponseEntity<String> cancel(@PathVariable Long id) {
        return transactionService.cancel(id);
    }
}