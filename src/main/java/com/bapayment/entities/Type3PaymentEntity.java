package com.bapayment.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "db_payment_type3")
@PrimaryKeyJoinColumn(name = "payment_id")
public class Type3PaymentEntity extends BasePaymentEntity {

    @Column(name="update_date", updatable=false)
    private LocalDateTime update_date;
    private String creditor_bic;

    public String getCreditor_bic() {
        return creditor_bic;
    }

    public void setCreditor_bic(String creditor_bic) {
        this.creditor_bic = creditor_bic;
    }
}
