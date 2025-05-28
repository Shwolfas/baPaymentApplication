package com.bapayment.entities;

import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;
import jakarta.persistence.*;
@Entity
public abstract class BasePaymentEntity
{
    @Id
    private long id;
    @Enumerated(EnumType.STRING)
    private PaymentTypesEnum type;
    private double amount;
    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;
    private String debtor_iban;
    private String creditor_iban;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
}
