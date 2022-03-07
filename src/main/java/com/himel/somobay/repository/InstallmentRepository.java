package com.himel.somobay.repository;

import com.himel.somobay.model.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
    List<Installment> findAllByLoanId(Long loanId);
}
