package com.himel.somobay.service;

import com.himel.somobay.model.DepositTransaction;

import java.util.List;

public interface DepositTransactionService {
    DepositTransaction addDepositTransaction(Long depositId, DepositTransaction depositTransaction);
    DepositTransaction approveDepositTransaction(Long depositTransactionId);
    List<DepositTransaction> getDepositTransactionsByDepositId(Long depositId);

}
