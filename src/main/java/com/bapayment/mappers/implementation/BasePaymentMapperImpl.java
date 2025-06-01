package com.bapayment.mappers.implementation;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class BasePaymentMapperImpl {
    protected void setApiBaseFields(BasePaymentApi entity, Map<String, Object> payload) {
        entity.setType(PaymentTypesEnum.valueOf(payload.get("type").toString()));
        entity.setAmount(Double.parseDouble(payload.get("amount").toString()));
        entity.setCurrency(CurrencyEnum.valueOf(payload.get("currency").toString()));
        entity.setCreditor_iban(payload.get("creditor_iban").toString());
        entity.setDebtor_iban(payload.get("debtor_iban").toString());
    }

    protected void apiToEntityBaseFields(BasePaymentEntity paymentEntity, BasePaymentApi paymentApi){
        paymentEntity.setType(paymentApi.getType());
        paymentEntity.setAmount(paymentApi.getAmount());
        paymentEntity.setCurrency(paymentApi.getCurrency());
        paymentEntity.setCreditor_iban(paymentApi.getCreditor_iban());
        paymentEntity.setDebtor_iban(paymentApi.getDebtor_iban());
    }

    protected void entityToApiBaseFields(BasePaymentApi paymentApi, BasePaymentEntity paymentEntity){
        paymentApi.setId(paymentEntity.getId());
        paymentApi.setType(paymentEntity.getType());
        paymentApi.setAmount(paymentEntity.getAmount());
        paymentApi.setCurrency(paymentEntity.getCurrency());
        paymentApi.setCreditor_iban(paymentEntity.getCreditor_iban());
        paymentApi.setDebtor_iban(paymentEntity.getDebtor_iban());;
        paymentApi.setCanceled(paymentEntity.isCanceled());
        paymentApi.setCancelation_fee(paymentEntity.getCancelation_fee());
    }


}
