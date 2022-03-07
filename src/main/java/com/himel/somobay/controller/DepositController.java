package com.himel.somobay.controller;

import com.himel.somobay.model.Deposit;
import com.himel.somobay.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepositController {
    private DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping(path = "/deposit/add")
    public ResponseEntity<?> addDeposit(@RequestParam("userId") Long userId,@RequestBody Deposit deposit){
        return ResponseEntity.ok(depositService.addDeposit(userId,deposit));
    }

    @PutMapping(path = "/deposit/approve")
    public ResponseEntity<?> approveDeposit(@RequestParam("depositId") Long depositId){
        return ResponseEntity.ok(depositService.approveDeposit(depositId));
    }

    @PutMapping(path = "/deposit/activate")
    public ResponseEntity<?> activateDeposit(@RequestParam("depositId") Long depositId){
        return ResponseEntity.ok(depositService.activateDeposit(depositId));
    }

    @PutMapping(path = "/deposit/deactivate")
    public ResponseEntity<?> deactivateDeposit(@RequestParam("depositId") Long depositId){
        return ResponseEntity.ok(depositService.deactivateDeposit(depositId));
    }

    @GetMapping(path = "/deposit/user")
    public ResponseEntity<?> getDepositByUser(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(depositService.getDepositsByUser(userId));
    }
    @GetMapping(path = "/deposit/getDeposit")
    public ResponseEntity<?> getDeposit(@RequestParam("depositId") Long depositId){
        return ResponseEntity.ok(depositService.getDeposit(depositId));
    }
}
