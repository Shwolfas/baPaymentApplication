package com.bapayment.mappers;

import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.enums.CurrencyEnum;
import com.bapayment.validations.implementation.BasePaymentValidationImpl;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public abstract class BasePaymentMapper {
    protected void setBaseFields(BasePaymentEntity entity, Map<String, Object> payload) {
        entity.setAmount(Double.parseDouble(payload.get("amount").toString()));
        entity.setCurrency(CurrencyEnum.valueOf(payload.get("currency").toString()));
        entity.setDebtor_iban(payload.get("debtor_iban").toString());
        entity.setCreditor_iban(payload.get("creditor_iban").toString());
    }
}
