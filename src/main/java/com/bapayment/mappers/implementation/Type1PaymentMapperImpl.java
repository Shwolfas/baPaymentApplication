package com.bapayment.mappers.implementation;

import com.bapayment.api.BasePaymentAPI;
import com.bapayment.api.Type1PaymentAPI;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.entities.Type1PaymentEntity;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.mappers.PaymentMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component("TYPE1Mapper")
public class Type1PaymentMapperImpl implements PaymentMapper {

    private final BasePaymentMapperImpl basePaymentMapper;

    public Type1PaymentMapperImpl(BasePaymentMapperImpl basePaymentMapper) {
        this.basePaymentMapper = basePaymentMapper;
    }

    @Override
    public String getType() {
        return PaymentTypesEnum.TYPE1.toString();
    }

    public BasePaymentAPI inputToAPI (Map<String, Object> payload) {
        Type1PaymentAPI type1PaymentAPI = new Type1PaymentAPI();
        basePaymentMapper.setAPIBaseFields(type1PaymentAPI, payload);
        type1PaymentAPI.setDetails((String) payload.get("details"));
        return type1PaymentAPI;
    }

    @Override
    public BasePaymentEntity apiToEntity(BasePaymentAPI basePaymentAPI) {
        Type1PaymentEntity type1PaymentEntity = new Type1PaymentEntity();
        Type1PaymentAPI type1PaymentAPI = (Type1PaymentAPI) basePaymentAPI;

        basePaymentMapper.apiToEntityBaseFields(type1PaymentEntity, basePaymentAPI);
        type1PaymentEntity.setDetails(type1PaymentAPI.getDetails());
        return type1PaymentEntity;
    }

    @Override
    public BasePaymentAPI entityToApi(BasePaymentEntity basePaymentEntity) {
        Type1PaymentAPI type1PaymentAPI = new Type1PaymentAPI();
        Type1PaymentEntity type1PaymentEntity = (Type1PaymentEntity) basePaymentEntity;

        basePaymentMapper.entityToApiBaseFields(type1PaymentAPI, basePaymentEntity);
        type1PaymentAPI.setDetails(type1PaymentEntity.getDetails());
        return type1PaymentAPI;
    }


}
