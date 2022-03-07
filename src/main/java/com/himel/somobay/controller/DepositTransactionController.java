package com.himel.somobay.controller;

import com.himel.somobay.model.DepositTransaction;
import com.himel.somobay.service.DepositTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepositTransactionController {

    private DepositTransactionService depositTransactionService;

    @Autowired
    public DepositTransactionController(DepositTransactionService depositTransactionService) {
        this.depositTransactionService = depositTransactionService;
    }

    @PostMapping(path = "/deposit/transaction/add")
    public ResponseEntity<DepositTransaction> addDepositTransaction(@RequestParam(value = "depositId") Long depositId, @RequestBody DepositTransaction depositTransaction){
        return ResponseEntity.ok(depositTransactionService.addDepositTransaction(depositId,depositTransaction));
    }

    @GetMapping(path = "/deposit/transaction/getByDepositId")
    public ResponseEntity<List<DepositTransaction>> getByDepositId(@RequestParam(value = "depositId") Long depositId){
        return ResponseEntity.ok(depositTransactionService.getDepositTransactionsByDepositId(depositId));
    }

    @PutMapping(path = "/deposit/transaction/approve")
    public ResponseEntity<DepositTransaction> approveDepositTransaction(@RequestParam(value = "depositTransactionId") Long depositTransactionId){
        return ResponseEntity.ok(depositTransactionService.approveDepositTransaction(depositTransactionId));
    }
}
