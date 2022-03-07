package com.himel.somobay.service;

import com.himel.somobay.model.Deposit;

import java.util.List;

public interface DepositService {
    Deposit addDeposit(Long userId,Deposit deposit);
    Deposit getDeposit(Long depositId);
    List<Deposit> getDeposits();
    List<Deposit> getDepositsByUser(Long userId);
    Deposit editDeposit(Long depositId, Deposit deposit);
    Deposit approveDeposit(Long depositId);
    Deposit activateDeposit(Long depositId);
    Deposit deactivateDeposit(Long depositId);
}
