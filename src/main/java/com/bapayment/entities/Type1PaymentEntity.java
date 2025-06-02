package com.bapayment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "db_payment_type1")
@PrimaryKeyJoinColumn(name = "payment_id")
public class Type1PaymentEntity extends BasePaymentEntity {
    private String details;


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
