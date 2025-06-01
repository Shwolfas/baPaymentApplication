package com.bapayment.service.implementation;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.repositories.BasePaymentRepository;
import com.bapayment.service.PaymentService;
import com.bapayment.validations.implementation.BasePaymentValidationImpl;
import org.hibernate.annotations.NotFound;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final BasePaymentRepository basePaymentRepository;

    private final BasePaymentValidationImpl basePaymentValidation;

    public PaymentServiceImpl(BasePaymentRepository basePaymentRepository,
                              BasePaymentValidationImpl basePaymentValidation) {
        this.basePaymentRepository = basePaymentRepository;
        this.basePaymentValidation = basePaymentValidation;
    }

    @Override
    public void save(BasePaymentEntity payment) {
        basePaymentRepository.save(payment);
    }

    @Override
    public ResponseEntity<String> cancelPayment(Long id) {
        Optional<BasePaymentEntity> fetchedPaymentEntity = basePaymentRepository.findById(id);
        if (fetchedPaymentEntity.isEmpty()) {
            return ResponseEntity.badRequest().body("Payment not found");
        }
        BasePaymentEntity basePaymentEntity = fetchedPaymentEntity.get();
        try {
            basePaymentValidation.validateBaseCancelation(basePaymentEntity);

            LocalDateTime creationTime = basePaymentEntity.getInsert_date();
            Long duration =  Duration.between(creationTime, LocalDateTime.now()).toHours();
            Double cancelationFee = duration * basePaymentEntity.getType().getCancellationCoef();

            basePaymentEntity.setCanceled(true);
            basePaymentEntity.setCancelation_fee(cancelationFee);

            basePaymentRepository.save(basePaymentEntity);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Payment #" + basePaymentEntity.getId() + " is already cancelled");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unexpected error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Payment cancelled successfully");
    }

    public List<BasePaymentEntity> getAll() {
        return basePaymentRepository.findAll();
    }
}
