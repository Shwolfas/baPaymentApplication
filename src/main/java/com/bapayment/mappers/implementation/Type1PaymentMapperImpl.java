package com.bapayment.mappers.implementation;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.api.Type1PaymentApi;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.entities.Type1PaymentEntity;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.mappers.PaymentMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component("TYPE1Mapper")
public class Type1PaymentMapperImpl implements PaymentMapper {

    private final BasePaymentMapper basePaymentMapper;

    public Type1PaymentMapperImpl(BasePaymentMapper basePaymentMapper) {
        this.basePaymentMapper = basePaymentMapper;
    }

    @Override
    public String getType() {
        return PaymentTypesEnum.TYPE1.toString();
    }

    public BasePaymentApi inputToApi (Map<String, Object> payload) {
        Type1PaymentApi type1PaymentAPI = new Type1PaymentApi();
        basePaymentMapper.setApiBaseFields(type1PaymentAPI, payload);
        type1PaymentAPI.setDetails((String) payload.get("details"));
        return type1PaymentAPI;
    }

    @Override
    public BasePaymentEntity apiToEntity(BasePaymentApi basePaymentAPI) {
        Type1PaymentEntity type1PaymentEntity = new Type1PaymentEntity();
        Type1PaymentApi type1PaymentAPI = (Type1PaymentApi) basePaymentAPI;

        basePaymentMapper.apiToEntityBaseFields(type1PaymentEntity, basePaymentAPI);
        type1PaymentEntity.setDetails(type1PaymentAPI.getDetails());
        return type1PaymentEntity;
    }

    @Override
    public BasePaymentApi entityToApi(BasePaymentEntity basePaymentEntity) {
        Type1PaymentApi type1PaymentAPI = new Type1PaymentApi();
        Type1PaymentEntity type1PaymentEntity = (Type1PaymentEntity) basePaymentEntity;

        basePaymentMapper.entityToApiBaseFields(type1PaymentAPI, basePaymentEntity);
        type1PaymentAPI.setDetails(type1PaymentEntity.getDetails());
        return type1PaymentAPI;
    }
}
