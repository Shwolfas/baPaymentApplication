package com.bapayment.entities;

public class Type3Payment extends BasePayment {

    private String creditor_bic;

    public String getCreditor_bic() {
        return creditor_bic;
    }

    public void setCreditor_bic(String creditor_bic) {
        this.creditor_bic = creditor_bic;
    }
}
