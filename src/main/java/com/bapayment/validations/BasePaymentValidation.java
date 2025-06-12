package com.bapayment.validations;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.entities.BasePaymentEntity;

public interface BasePaymentValidation {
    void validateBase(BasePaymentApi basePaymentAPI);

    void validateBaseCancelation(BasePaymentEntity basePaymentEntity) throws Exception;
}
