package com.himel.somobay.repository;

import com.himel.somobay.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit,Long> {
    List<Deposit> findAllByUserUserId(Long userId);
}
