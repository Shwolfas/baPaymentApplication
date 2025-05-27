package com.bapayment.entities;

import com.bapayment.entities.enums.CurrencyEnum;
import com.bapayment.entities.enums.PaymentTypesEnum;

public abstract class BasePayment {
    private PaymentTypesEnum type;
    private double amount;
    private CurrencyEnum currency;
    private String debtor_iban, creditor_iban;

    public PaymentTypesEnum getType() {
        return type;
    }

    public void setType(PaymentTypesEnum type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public String getDebtor_iban() {
        return debtor_iban;
    }

    public void setDebtor_iban(String debtor_iban) {
        this.debtor_iban = debtor_iban;
    }

    public String getCreditor_iban() {
        return creditor_iban;
    }

    public void setCreditor_iban(String creditor_iban) {
        this.creditor_iban = creditor_iban;
    }
}
