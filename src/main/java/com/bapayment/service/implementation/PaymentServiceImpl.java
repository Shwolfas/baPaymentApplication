package com.bapayment.service.implementation;

import com.bapayment.api.query.CancelFeeView;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.exceptions.NotSameDayCancelationException;
import com.bapayment.exceptions.PaymentAlreadyCanceledException;
import com.bapayment.repositories.BasePaymentRepository;
import com.bapayment.service.PaymentService;
import com.bapayment.validations.BasePaymentValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final BasePaymentRepository basePaymentRepository;

    private final BasePaymentValidation basePaymentValidation;


    public PaymentServiceImpl(BasePaymentRepository basePaymentRepository,
                              BasePaymentValidation basePaymentValidation) {
        this.basePaymentRepository = basePaymentRepository;
        this.basePaymentValidation = basePaymentValidation;
    }

    @Override
    public void save(BasePaymentEntity payment) {
        basePaymentRepository.save(payment);
    }

    @Override
    public ResponseEntity<String> cancelPayment(Long id) throws Exception {
        Optional<BasePaymentEntity> fetchedPaymentEntity = basePaymentRepository.findById(id);

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

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
            basePaymentEntity.setCancelation_fee(Double.valueOf(decimalFormat.format(cancelationFee)));

            basePaymentRepository.save(basePaymentEntity);
        } catch (NotSameDayCancelationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (PaymentAlreadyCanceledException e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unexpected error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Payment cancelled successfully");
    }

    public List<BasePaymentEntity> getAll() {
        return basePaymentRepository.findAll();
    }

    @Override
    public Optional<CancelFeeView> getPaymentById(Long id) {
        return basePaymentRepository.findPaymentById(id);
    }

    @Override
    public List<Long> getAllValid() {
        return basePaymentRepository.findAllValid();
    }

    @Override
    public List<Long> getAllValidByAmount(Double amount) {
        return basePaymentRepository.findAllValidByAmount(amount);
    }


}
