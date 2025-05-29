package com.bapayment.repositories;

import com.bapayment.entities.BasePaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasePaymentRepository extends JpaRepository<BasePaymentEntity, Long> {
}
