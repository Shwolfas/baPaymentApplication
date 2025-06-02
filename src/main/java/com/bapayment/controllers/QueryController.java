package com.bapayment.controllers;

import com.bapayment.api.query.CancelFeeView;
import com.bapayment.service.implementation.PaymentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class QueryController {
    private final PaymentServiceImpl paymentService;

    public QueryController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/search/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<CancelFeeView> cancelation_fee;
        try {
            if (Objects.isNull(id)) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("You must provide payment ID");
            }

            cancelation_fee = paymentService.getPaymentById(id);

            if (!cancelation_fee.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No record with " + id + " exists in database");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
        return ResponseEntity.ok().body(cancelation_fee.get());
    }

    @GetMapping("/search/getAllValid")
    public ResponseEntity<?> getAllValid(){
        try {
            List<Long> idList = paymentService.getAllValid();
            return ResponseEntity.ok().body(idList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/search/getAllValid/amount/{amount}")
    public ResponseEntity<?> getAllValidByAmount(@PathVariable String amount){
        try {
            List<Long> idList = paymentService.getAllValidByAmount(Double.parseDouble(amount.replace(',', '.')));
            return ResponseEntity.ok().body(idList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }

}
