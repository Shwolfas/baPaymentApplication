package com.bapayment.mappers.implementation;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.entities.BasePaymentEntity;

import java.util.Map;

public interface BasePaymentMapper {
    void setApiBaseFields(BasePaymentApi entity, Map<String, Object> payload);

    void apiToEntityBaseFields(BasePaymentEntity paymentEntity, BasePaymentApi paymentApi);

    void entityToApiBaseFields(BasePaymentApi paymentApi, BasePaymentEntity paymentEntity);
}
