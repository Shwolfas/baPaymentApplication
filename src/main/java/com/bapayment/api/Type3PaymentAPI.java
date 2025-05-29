package com.bapayment.api;

import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;

public class Type3PaymentAPI extends BasePaymentAPI{
    private String creditor_bic;

    public String getCreditor_bic() {
        return creditor_bic;
    }

    public void setCreditor_bic(String creditor_bic) {
        this.creditor_bic = creditor_bic;
    }
}
