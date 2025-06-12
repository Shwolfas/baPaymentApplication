package com.bapayment.service.implementation;

import com.bapayment.api.query.CancelFeeView;
import com.bapayment.service.PaymentService;
import com.bapayment.service.QueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class QueryServiceImpl implements QueryService {

    private final PaymentService paymentService;

    public QueryServiceImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public ResponseEntity<String> getById(Long id) {
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
        return ResponseEntity.ok().body(cancelation_fee.get().toString());
    }

    @Override
    public ResponseEntity<String> getAllValid() {
        try {
            List<Long> idList = paymentService.getAllValid();
            return ResponseEntity.ok().body(idList.toString());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> getAllValidByAmount(String amount) {
        try {
            List<Long> idList = paymentService.getAllValidByAmount(Double.parseDouble(amount.replace(',', '.')));
            return ResponseEntity.ok().body(idList.toString());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }
}
