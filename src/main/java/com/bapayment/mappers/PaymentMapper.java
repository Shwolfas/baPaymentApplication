package com.bapayment.mappers;

import com.bapayment.entities.BasePaymentEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public interface PaymentMapper {
    String getType();
    BasePaymentEntity toEntity(Map<String, Object> payload);

    void validate(BasePaymentEntity basePaymentEntity);
}
