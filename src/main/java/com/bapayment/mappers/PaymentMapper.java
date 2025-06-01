package com.bapayment.mappers;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.entities.BasePaymentEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public interface PaymentMapper {
    String getType();

    BasePaymentEntity apiToEntity(BasePaymentApi basePaymentAPI);

    BasePaymentApi entityToApi (BasePaymentEntity basePaymentEntity);

    BasePaymentApi inputToApi (Map<String, Object> payload);

}
