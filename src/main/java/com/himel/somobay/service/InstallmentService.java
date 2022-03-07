package com.himel.somobay.service;

import com.himel.somobay.model.Installment;

import java.util.List;

public interface InstallmentService {
    Installment addInstallment(Long loanId, Installment installment);
    Installment approveInstallment(Long installmentId);
    List<Installment> getInstallmentsByLoanId(Long loanId);
}


//    DepositTransaction addDepositTransaction(Long depositId, DepositTransaction depositTransaction);
//    DepositTransaction approveDepositTransaction(Long depositTransactionId);
//    List<DepositTransaction> getDepositTransactionsByDepositId(Long depositId);
