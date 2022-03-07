package com.himel.somobay.controller;

import com.himel.somobay.model.Deposit;
import com.himel.somobay.model.Loan;
import com.himel.somobay.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {

    private LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping(path = "/loan/add")
    public ResponseEntity<?> addLoan(@RequestParam("userId") Long userId,@RequestBody Loan loan){
        return ResponseEntity.ok(loanService.addLoan(userId,loan));
    }

    @PutMapping(path = "/loan/approve")
    public ResponseEntity<?> approveLoan(@RequestParam("loanId") Long loanId){
        return ResponseEntity.ok(loanService.approveLoan(loanId));
    }

    @PutMapping(path = "/loan/activate")
    public ResponseEntity<?> activateLoan(@RequestParam("loanId") Long loanId){
        return ResponseEntity.ok(loanService.activateLoan(loanId));
    }

    @PutMapping(path = "/loan/deactivate")
    public ResponseEntity<?> deactivateLoan(@RequestParam("loanId") Long loanId){
        return ResponseEntity.ok(loanService.deactivateLoan(loanId));
    }

    @GetMapping(path = "/loan/getByUser")
    public ResponseEntity<?> getLoanByUser(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(loanService.getLoansByUser(userId));
    }

    @GetMapping(path = "/loan/getLoan")
    public ResponseEntity<?> getLoan(@RequestParam("loanId") Long loanId){
        return ResponseEntity.ok(loanService.getLoan(loanId));
    }

}
