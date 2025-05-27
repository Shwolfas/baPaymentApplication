package com.bapayment.repositories;

import com.bapayment.entities.BasePayment;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private final List<BasePayment> payments = new ArrayList<>();

    public void save(BasePayment payment) {
        payments.add(payment);
    }

    public List<BasePayment> getAll() {
        return payments;
    }
}
