package com.himel.somobay.repository;

import com.himel.somobay.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findAllByUserUserId(Long userId);
}
