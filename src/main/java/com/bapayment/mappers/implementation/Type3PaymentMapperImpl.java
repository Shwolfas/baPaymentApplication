package com.bapayment.mappers.implementation;

import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.entities.Type2PaymentEntity;
import com.bapayment.entities.Type3PaymentEntity;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.mappers.BasePaymentMapper;
import com.bapayment.mappers.PaymentMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class Type3PaymentMapperImpl extends BasePaymentMapper implements PaymentMapper {
    @Override
    public String getType() {
        return PaymentTypesEnum.TYPE3.toString();
    }

    @Override
    public BasePaymentEntity toEntity(Map<String, Object> payload) {
        Type3PaymentEntity type3PaymentEntity = new Type3PaymentEntity();
        setBaseFields(type3PaymentEntity, payload);
        type3PaymentEntity.setCreditor_bic((String) payload.get("creditor_bic"));
        return type3PaymentEntity;
    }

    @Override
    public void validate(BasePaymentEntity basePaymentEntity) {

    }
}
