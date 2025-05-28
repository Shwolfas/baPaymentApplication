package com.bapayment.mappers.implementation;

import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.entities.Type1PaymentEntity;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.mappers.BasePaymentMapper;
import com.bapayment.mappers.PaymentMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class Type1PaymentMapperImpl extends BasePaymentMapper implements PaymentMapper {
    @Override
    public String getType() {
        return PaymentTypesEnum.TYPE1.toString();
    }

    @Override
    public BasePaymentEntity toEntity(Map<String, Object> payload) {
        Type1PaymentEntity type1PaymentEntity = new Type1PaymentEntity();
        setBaseFields(type1PaymentEntity, payload);
        type1PaymentEntity.setDetails((String) payload.get("details"));
        return type1PaymentEntity;
    }

    @Override
    public void validate(BasePaymentEntity basePaymentEntity) {

    }
}
