package com.bapayment.controllers;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.mappers.PaymentMapper;
import com.bapayment.service.implementation.PaymentServiceImpl;
import com.bapayment.validations.PaymentValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class TransactionController {

    private final PaymentServiceImpl paymentService;

    private final Map<String, PaymentMapper> paymentMappers;

    private final Map<String, PaymentValidation> paymentValidations;

    public TransactionController(PaymentServiceImpl paymentService,
                                 Map<String, PaymentMapper> paymentMappers,
                                 Map<String, PaymentValidation> paymentValdiations) {

        this.paymentService = paymentService;
        this.paymentMappers = paymentMappers;
        this.paymentValidations = paymentValdiations;
    }

    @GetMapping("/getPayments")
    public ResponseEntity<?> getAll() {
        List<BasePaymentApi> basePaymentApiList;
        try {
            List<BasePaymentEntity> basePaymentEntityList = paymentService.getAll();
            basePaymentApiList = basePaymentEntityList.stream().map(b -> {

                PaymentMapper mapper = paymentMappers.get(b.getType()+"Mapper");
                return mapper.entityToApi(b);

            }).toList();
            return ResponseEntity.ok(basePaymentApiList);
        }  catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }

    }

    @PostMapping("/newPayment")
    public ResponseEntity<String> save(@RequestBody Map<String, Object> payload) {
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
            return ResponseEntity.ok().body("Payment created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }

    @PatchMapping("/cancelPayment/{id}")
    public ResponseEntity<String> cancel(@PathVariable Long id) {
        try {
            return paymentService.cancelPayment(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unexpected error: " + e.getMessage());
        }

    }
}