package com.acme.test01.levanchitiashvili.controllers.accounts;

import com.acme.test01.levanchitiashvili.exceptions.AccountNotFoundException;
import com.acme.test01.levanchitiashvili.exceptions.WithdrawalAmountTooLargeException;
import com.acme.test01.levanchitiashvili.models.accounts.Account;
import com.acme.test01.levanchitiashvili.services.accounts.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("{accountId}")
    public Account get(@PathVariable Long accountId) throws AccountNotFoundException{
        return accountService.get(accountId);
    }

    @PostMapping("savings")
    public void openSavingsAccount(@RequestParam BigDecimal amountToDeposit, @RequestParam String customerNum){
        accountService.openSavingsAccount(amountToDeposit,customerNum);
    }


    @PostMapping("current")
    public void openCurrentAccount(@RequestParam BigDecimal overdraftAmount,@RequestParam String customerNum){
        accountService.openCurrentAccount(overdraftAmount,customerNum);
    }

    @PutMapping("{accountId}/withdraw")
    public void withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amountToWithdraw)
            throws AccountNotFoundException, WithdrawalAmountTooLargeException{
        accountService.withdraw(accountId,amountToWithdraw);
    }

    @PutMapping("{accountId}/deposit")
    public void deposit(@PathVariable Long accountId, @RequestParam BigDecimal amountToDeposit) throws AccountNotFoundException{
        accountService.deposit(accountId,amountToDeposit);
    }
}