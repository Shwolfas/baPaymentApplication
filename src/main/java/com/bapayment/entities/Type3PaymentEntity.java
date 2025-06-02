package com.bapayment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "db_payment_type3")
@PrimaryKeyJoinColumn(name = "payment_id")
public class Type3PaymentEntity extends BasePaymentEntity {
    private String creditor_bic;

    public String getCreditor_bic() {
        return creditor_bic;
    }

    public void setCreditor_bic(String creditor_bic) {
        this.creditor_bic = creditor_bic;
    }
}
