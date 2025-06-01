package com.bapayment.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "db_payment_type2")
@PrimaryKeyJoinColumn(name = "payment_id")
public class Type2PaymentEntity extends BasePaymentEntity{

    @Column(name="update_date", updatable=false)
    private LocalDateTime update_date;

    private String details;


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
