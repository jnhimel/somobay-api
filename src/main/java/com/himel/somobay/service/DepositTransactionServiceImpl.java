package com.himel.somobay.service;

import com.himel.somobay.exceptions.DepositNotActiveException;
import com.himel.somobay.exceptions.DepositNotFoundException;
import com.himel.somobay.model.Deposit;
import com.himel.somobay.model.DepositTransaction;
import com.himel.somobay.repository.DepositTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepositTransactionServiceImpl implements DepositTransactionService{

    private DepositTransactionRepository depositTransactionRepository;

    private DepositService depositService;

    @Autowired
    public DepositTransactionServiceImpl(DepositTransactionRepository depositTransactionRepository, DepositService depositService) {
        this.depositTransactionRepository = depositTransactionRepository;
        this.depositService = depositService;
    }

    @Override
    public DepositTransaction addDepositTransaction(Long depositId, DepositTransaction depositTransaction) {
        Deposit deposit = depositService.getDeposit(depositId);
        if(deposit.isApproved()&&deposit.isActive()){
            depositTransaction.setDate(LocalDateTime.now());
            depositTransaction.setApproved(false);
            depositTransaction.setDeposit(deposit);
            return depositTransactionRepository.save(depositTransaction);
        }
        else {
            throw new DepositNotActiveException("Deposit not active or not approved");
        }

    }

    @Override
    public DepositTransaction approveDepositTransaction(Long depositTransactionId) {
        DepositTransaction transaction = depositTransactionRepository.getById(depositTransactionId);
        transaction.setApproved(true);
        return depositTransactionRepository.save(transaction);
    }

    @Override
    public List<DepositTransaction> getDepositTransactionsByDepositId(Long depositId) {
        return depositTransactionRepository.findAllByDepositId(depositId);
    }
}
