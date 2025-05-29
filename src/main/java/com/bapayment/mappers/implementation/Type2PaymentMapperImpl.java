package com.bapayment.mappers.implementation;

import com.bapayment.api.BasePaymentAPI;
import com.bapayment.api.Type2PaymentAPI;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.entities.Type2PaymentEntity;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.mappers.PaymentMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("TYPE2Mapper")
public class Type2PaymentMapperImpl implements PaymentMapper {

    private final BasePaymentMapperImpl basePaymentMapper;

    public Type2PaymentMapperImpl(BasePaymentMapperImpl basePaymentMapper) {
        this.basePaymentMapper = basePaymentMapper;
    }

    @Override
    public String getType() {
        return PaymentTypesEnum.TYPE2.toString();
    }

    public BasePaymentAPI inputToAPI (Map<String, Object> payload) {
        Type2PaymentAPI type2PaymentAPI = new Type2PaymentAPI();
        basePaymentMapper.setAPIBaseFields(type2PaymentAPI, payload);
        type2PaymentAPI.setDetails((String) payload.get("details"));
        return type2PaymentAPI;
    }

    @Override
    public BasePaymentEntity apiToEntity(BasePaymentAPI basePaymentAPI) {
        Type2PaymentEntity type2PaymentEntity = new Type2PaymentEntity();
        Type2PaymentAPI type2PaymentAPI = (Type2PaymentAPI) basePaymentAPI;

        basePaymentMapper.apiToEntityBaseFields(type2PaymentEntity, basePaymentAPI);
        type2PaymentEntity.setDetails(type2PaymentAPI.getDetails());
        return type2PaymentEntity;
    }

    @Override
    public BasePaymentAPI entityToApi(BasePaymentEntity basePaymentEntity) {
        Type2PaymentAPI type2PaymentAPI = new Type2PaymentAPI();
        Type2PaymentEntity type2PaymentEntity = (Type2PaymentEntity) basePaymentEntity;

        basePaymentMapper.entityToApiBaseFields(type2PaymentAPI, basePaymentEntity);
        type2PaymentAPI.setDetails(type2PaymentEntity.getDetails());
        return type2PaymentAPI;
    }


}
