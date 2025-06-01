package com.bapayment.api;

public class Type3PaymentApi extends BasePaymentApi {
    private String creditor_bic;

    public String getCreditor_bic() {
        return creditor_bic;
    }

    public void setCreditor_bic(String creditor_bic) {
        this.creditor_bic = creditor_bic;
    }
}
