package com.bapayment.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Type3PaymentEntity extends BasePaymentEntity {

    @Id
    @GeneratedValue
    private long id;
    private String creditor_bic;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreditor_bic() {
        return creditor_bic;
    }

    public void setCreditor_bic(String creditor_bic) {
        this.creditor_bic = creditor_bic;
    }
}
