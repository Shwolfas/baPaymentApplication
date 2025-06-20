package com.bapayment.repositories;

import com.bapayment.api.query.CancelFeeView;
import com.bapayment.entities.BasePaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BasePaymentRepository extends JpaRepository<BasePaymentEntity, Long> {
    @Transactional
    @Query("select p.id as id, p.cancelation_fee as cancelation_fee from BasePaymentEntity p where p.id =?1")
    Optional<CancelFeeView> findPaymentById(Long id);

    @Transactional
    @Query("select p.id from BasePaymentEntity p where p.canceled = false")
    List<Long> findAllValid();

    @Transactional
    @Query("select p.id from BasePaymentEntity p where p.canceled = false and p.amount=?1")
    List<Long> findAllValidByAmount(Double amount);
}
