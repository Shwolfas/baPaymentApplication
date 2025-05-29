package com.bapayment.mappers.implementation;

import com.bapayment.api.BasePaymentAPI;
import com.bapayment.api.Type3PaymentAPI;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.entities.Type3PaymentEntity;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.mappers.PaymentMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("TYPE3Mapper")
public class Type3PaymentMapperImpl implements PaymentMapper {

    private final BasePaymentMapperImpl basePaymentMapper;

    public Type3PaymentMapperImpl(BasePaymentMapperImpl basePaymentMapper) {
        this.basePaymentMapper = basePaymentMapper;
    }

    @Override
    public String getType() {
        return PaymentTypesEnum.TYPE3.toString();
    }

    public BasePaymentAPI inputToAPI (Map<String, Object> payload) {
        Type3PaymentAPI type3PaymentAPI = new Type3PaymentAPI();
        basePaymentMapper.setAPIBaseFields(type3PaymentAPI, payload);
        type3PaymentAPI.setCreditor_bic((String) payload.get("creditor_bic"));
        return type3PaymentAPI;
    }

    @Override
    public BasePaymentEntity apiToEntity(BasePaymentAPI basePaymentAPI) {
        Type3PaymentEntity type3PaymentEntity = new Type3PaymentEntity();
        Type3PaymentAPI type3PaymentAPI = (Type3PaymentAPI) basePaymentAPI;

        basePaymentMapper.apiToEntityBaseFields(type3PaymentEntity, basePaymentAPI);
        type3PaymentEntity.setCreditor_bic(type3PaymentAPI.getCreditor_bic());
        return type3PaymentEntity;
    }

    @Override
    public BasePaymentAPI entityToApi(BasePaymentEntity basePaymentEntity) {
        Type3PaymentAPI type3PaymentAPI = new Type3PaymentAPI();
        Type3PaymentEntity type3PaymentEntity = (Type3PaymentEntity) basePaymentEntity;

        basePaymentMapper.entityToApiBaseFields(type3PaymentAPI, basePaymentEntity);
        type3PaymentAPI.setCreditor_bic(type3PaymentEntity.getCreditor_bic());
        return type3PaymentAPI;
    }


}
