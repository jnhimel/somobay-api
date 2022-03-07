package com.himel.somobay.service;

import com.himel.somobay.exceptions.DepositNotFoundException;
import com.himel.somobay.model.Deposit;
import com.himel.somobay.model.User;
import com.himel.somobay.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositServiceImpl implements DepositService{

    private DepositRepository depositRepository;
    private UserService userService;

    @Autowired
    public DepositServiceImpl(DepositRepository depositRepository, UserService userService) {
        this.depositRepository = depositRepository;
        this.userService = userService;
    }


    @Override
    public Deposit addDeposit(Long userId,Deposit deposit) {
        User user = userService.getUser(userId);
        deposit.setUser(user);
        return depositRepository.save(deposit);
    }

    @Override
    public Deposit getDeposit(Long depositId) {
        return depositRepository.findById(depositId).orElseThrow(()->new DepositNotFoundException("Deposit not found"));
    }

    @Override
    public List<Deposit> getDeposits() {
        return depositRepository.findAll();
    }

    @Override
    public List<Deposit> getDepositsByUser(Long userId) {
        return depositRepository.findAllByUserUserId(userId);
    }

    @Override
    public Deposit editDeposit(Long depositId, Deposit deposit) {
        Deposit currentDeposit = getDeposit(depositId);
        currentDeposit.setDepositAmount(deposit.getDepositAmount());
        currentDeposit.setDuration(deposit.getDuration());
        currentDeposit.setPeriod(deposit.getPeriod());
        currentDeposit.setTargetAmount(deposit.getTargetAmount());
        return depositRepository.save(currentDeposit);
    }

    @Override
    public Deposit approveDeposit(Long depositId) {
        Deposit currentDeposit = getDeposit(depositId);
        currentDeposit.setApproved(true);
        return depositRepository.save(currentDeposit);
    }

    @Override
    public Deposit activateDeposit(Long depositId) {
        Deposit currentDeposit = getDeposit(depositId);
        currentDeposit.setActive(true);
        return depositRepository.save(currentDeposit);
    }

    @Override
    public Deposit deactivateDeposit(Long depositId) {
        Deposit currentDeposit = getDeposit(depositId);
        currentDeposit.setActive(false);
        return depositRepository.save(currentDeposit);
    }

}
