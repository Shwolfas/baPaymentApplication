package com.bapayment.mappers;

import com.bapayment.api.BasePaymentAPI;
import com.bapayment.entities.BasePaymentEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public interface PaymentMapper {
    String getType();

    BasePaymentEntity apiToEntity(BasePaymentAPI basePaymentAPI);

    BasePaymentAPI entityToApi (BasePaymentEntity basePaymentEntity);

    BasePaymentAPI inputToAPI (Map<String, Object> payload);

}
