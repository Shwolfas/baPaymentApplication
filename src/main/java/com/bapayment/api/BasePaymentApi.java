package com.bapayment.api;

import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;

public abstract class BasePaymentApi {

    private Long id;
    private PaymentTypesEnum type;
    private double amount;
    private CurrencyEnum currency;
    private String debtor_iban;
    private String creditor_iban;
    private boolean canceled;

    private Long cancelation_fee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public Long getCancelation_fee() {
        return cancelation_fee;
    }

    public void setCancelation_fee(Long cancelation_fee) {
        this.cancelation_fee = cancelation_fee;
    }
}
