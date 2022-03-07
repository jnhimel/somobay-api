package com.himel.somobay.controller;

import com.himel.somobay.model.Installment;
import com.himel.somobay.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstallmentController {
    private InstallmentService installmentService;

    @Autowired
    public InstallmentController(InstallmentService installmentService) {
        this.installmentService = installmentService;
    }

    @PostMapping(path = "/loan/installment/add")
    public ResponseEntity<Installment> addDepositTransaction(@RequestParam(value = "loanId") Long loanId, @RequestBody Installment installment){
        return ResponseEntity.ok(installmentService.addInstallment(loanId,installment));
    }

    @GetMapping(path = "/loan/installment/getByLoanId")
    public ResponseEntity<List<Installment>> getInstallmentByLoanId(@RequestParam(value = "loanId") Long loanId){
        return ResponseEntity.ok(installmentService.getInstallmentsByLoanId(loanId));
    }

    @PutMapping(path = "/loan/installment/approve")
    public ResponseEntity<Installment> approveInstallment(@RequestParam(value = "installmentId") Long installmentId){
        return ResponseEntity.ok(installmentService.approveInstallment(installmentId));
    }

}