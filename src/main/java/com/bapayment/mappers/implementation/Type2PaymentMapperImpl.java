package com.bapayment.mappers.implementation;

import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.entities.Type1PaymentEntity;
import com.bapayment.entities.Type2PaymentEntity;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.mappers.BasePaymentMapper;
import com.bapayment.mappers.PaymentMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class Type2PaymentMapperImpl extends BasePaymentMapper implements PaymentMapper {

    @Override
    public String getType() {
        return PaymentTypesEnum.TYPE2.toString();
    }

    @Override
    public BasePaymentEntity toEntity(Map<String, Object> payload) {
        Type2PaymentEntity type2PaymentEntity = new Type2PaymentEntity();
        setBaseFields(type2PaymentEntity, payload);
        type2PaymentEntity.setDetails((String) payload.get("details"));
        return type2PaymentEntity;
    }

    @Override
    public void validate(BasePaymentEntity basePaymentEntity) {

    }
}
