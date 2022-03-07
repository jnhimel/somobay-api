package com.himel.somobay.repository;

import com.himel.somobay.model.DepositTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositTransactionRepository extends JpaRepository<DepositTransaction,Long> {
    List<DepositTransaction> findAllByDepositId(Long depositId);
}
