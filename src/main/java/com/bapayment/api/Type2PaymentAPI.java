package com.bapayment.api;

import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;

public class Type2PaymentAPI extends BasePaymentAPI {
    private String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
