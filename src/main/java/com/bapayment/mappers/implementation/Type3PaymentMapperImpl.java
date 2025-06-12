package com.bapayment.mappers.implementation;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.api.Type3PaymentApi;
import com.bapayment.entities.BasePaymentEntity;
import com.bapayment.entities.Type3PaymentEntity;
import com.bapayment.enums.PaymentTypesEnum;
import com.bapayment.mappers.PaymentMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("TYPE3Mapper")
public class Type3PaymentMapperImpl implements PaymentMapper {

    private final BasePaymentMapper basePaymentMapper;

    public Type3PaymentMapperImpl(BasePaymentMapper basePaymentMapper) {
        this.basePaymentMapper = basePaymentMapper;
    }

    @Override
    public String getType() {
        return PaymentTypesEnum.TYPE3.toString();
    }

    public BasePaymentApi inputToApi (Map<String, Object> payload) {
        Type3PaymentApi type3PaymentAPI = new Type3PaymentApi();
        basePaymentMapper.setApiBaseFields(type3PaymentAPI, payload);
        type3PaymentAPI.setCreditor_bic((String) payload.get("creditor_bic"));
        return type3PaymentAPI;
    }

    @Override
    public BasePaymentEntity apiToEntity(BasePaymentApi basePaymentAPI) {
        Type3PaymentEntity type3PaymentEntity = new Type3PaymentEntity();
        Type3PaymentApi type3PaymentAPI = (Type3PaymentApi) basePaymentAPI;

        basePaymentMapper.apiToEntityBaseFields(type3PaymentEntity, basePaymentAPI);
        type3PaymentEntity.setCreditor_bic(type3PaymentAPI.getCreditor_bic());
        return type3PaymentEntity;
    }

    @Override
    public BasePaymentApi entityToApi(BasePaymentEntity basePaymentEntity) {
        Type3PaymentApi type3PaymentAPI = new Type3PaymentApi();
        Type3PaymentEntity type3PaymentEntity = (Type3PaymentEntity) basePaymentEntity;

        basePaymentMapper.entityToApiBaseFields(type3PaymentAPI, basePaymentEntity);
        type3PaymentAPI.setCreditor_bic(type3PaymentEntity.getCreditor_bic());
        return type3PaymentAPI;
    }


}
