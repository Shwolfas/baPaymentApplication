package com.bapayment.entities;

import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="db_payment")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BasePaymentEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Should be false, true for testing purposes of "Payment cancelation" functionality.
    @Column(name="insert_date", updatable=true)
    private LocalDateTime insert_date;

    @Column(name="update_date", updatable=false)
    private LocalDateTime update_date;

    @Enumerated(EnumType.STRING)
    private PaymentTypesEnum type;
    private double amount;
    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;
    private String debtor_iban;
    private String creditor_iban;

    private boolean canceled;

    //Always saved as EUR
    private double cancelation_fee;

    public Long getId() {
        return id;
    }

    @PostPersist
    protected void onInsert() {
        this.insert_date = LocalDateTime.now();
    }

    public LocalDateTime getInsert_date() {
        return insert_date;
    }

    @PostUpdate
    protected void onUpdate() {
        this.update_date = LocalDateTime.now();
    }

    public LocalDateTime getUpdate_date() {
        return update_date;
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

    public double getCancelation_fee() {
        return cancelation_fee;
    }

    public void setCancelation_fee(double cancelation_fee) {
        this.cancelation_fee = cancelation_fee;
    }
}
