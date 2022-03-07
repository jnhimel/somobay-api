package com.himel.somobay.service;

import com.himel.somobay.exceptions.DepositNotFoundException;
import com.himel.somobay.exceptions.LoanNotFoundException;
import com.himel.somobay.model.Loan;
import com.himel.somobay.model.User;
import com.himel.somobay.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService{

    private LoanRepository loanRepository;
    private UserService userService;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository, UserService userService) {
        this.loanRepository = loanRepository;
        this.userService = userService;
    }

    @Override
    public Loan addLoan(Long userId, Loan loan) {
        User user = userService.getUser(userId);
        loan.setUser(user);
        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoan(Long loanId) {
        return loanRepository.findById(loanId).orElseThrow(()->new LoanNotFoundException("Loan not found"));
    }

    @Override
    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    @Override
    public List<Loan> getLoansByUser(Long userId) {
        return loanRepository.findAllByUserUserId(userId);
    }

    @Override
    public Loan editLoan(Long loanId, Loan loan) {
        return null;
    }

    @Override
    public Loan approveLoan(Long loanId) {
        Loan loan = getLoan(loanId);
        loan.setApproved(true);
        return loanRepository.save(loan);
    }

    @Override
    public Loan activateLoan(Long loanId) {
        Loan loan = getLoan(loanId);
        loan.setActive(true);
        return loanRepository.save(loan);
    }

    @Override
    public Loan deactivateLoan(Long loanId) {
        Loan loan = getLoan(loanId);
        loan.setActive(false);
        return loanRepository.save(loan);
    }
}
