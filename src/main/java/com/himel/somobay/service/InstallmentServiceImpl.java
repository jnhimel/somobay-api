package com.himel.somobay.service;

import com.himel.somobay.exceptions.LoanNotActiveException;
import com.himel.somobay.model.Installment;
import com.himel.somobay.model.Loan;
import com.himel.somobay.repository.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InstallmentServiceImpl implements InstallmentService{

    private InstallmentRepository installmentRepository;
    private LoanService loanService;

    @Autowired
    public InstallmentServiceImpl(InstallmentRepository installmentRepository, LoanService loanService) {
        this.installmentRepository = installmentRepository;
        this.loanService = loanService;
    }

    @Override
    public Installment addInstallment(Long loanId, Installment installment) {
        Loan loan = loanService.getLoan(loanId);
        if(loan.isApproved() && loan.isActive()){
            installment.setDate(LocalDateTime.now());
            installment.setApproved(false);
            installment.setLoan(loan);
            return installmentRepository.save(installment);
        }
        else{
            throw new LoanNotActiveException("Loan not approved or not active!");
        }
    }

    @Override
    public Installment approveInstallment(Long installmentId) {
        Installment installment = installmentRepository.getById(installmentId);
        installment.setApproved(true);
        return installmentRepository.save(installment);
    }

    @Override
    public List<Installment> getInstallmentsByLoanId(Long loanId) {
        return installmentRepository.findAllByLoanId(loanId);
    }
}
