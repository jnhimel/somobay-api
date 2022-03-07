package com.himel.somobay.service;

import com.himel.somobay.model.Loan;

import java.util.List;

public interface LoanService {

    Loan addLoan(Long userId, Loan loan);
    Loan getLoan(Long loanId);
    List<Loan> getLoans();
    List<Loan> getLoansByUser(Long userId);
    Loan editLoan(Long loanId, Loan loan);
    Loan approveLoan(Long loanId);
    Loan activateLoan(Long loanId);
    Loan deactivateLoan(Long loanId);

}

//    Deposit addDeposit(Long userId, Deposit deposit);
//    Deposit getDeposit(Long depositId);
//    List<Deposit> getDeposits();
//    List<Deposit> getDepositsByUser(Long userId);
//    Deposit editDeposit(Long depositId, Deposit deposit);
//    Deposit approveDeposit(Long depositId);
//    Deposit activateDeposit(Long depositId);
//    Deposit deactivateDeposit(Long depositId);
