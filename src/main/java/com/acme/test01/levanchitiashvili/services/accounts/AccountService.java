package com.acme.test01.levanchitiashvili.services.accounts;

import com.acme.test01.levanchitiashvili.exceptions.AccountNotFoundException;
import com.acme.test01.levanchitiashvili.exceptions.WithdrawalAmountTooLargeException;
import com.acme.test01.levanchitiashvili.models.accounts.Account;

import java.math.BigDecimal;

public interface AccountService {
     Account get(Long id) throws AccountNotFoundException;
     Account save(Account account);

     void openSavingsAccount( BigDecimal amountToDeposit, String customerNum);
     void openCurrentAccount(BigDecimal overdraftAmount,String customerNum);
     void withdraw(Long accountId, BigDecimal amountToWithdraw)
            throws AccountNotFoundException, WithdrawalAmountTooLargeException;
     void deposit(Long accountId, BigDecimal amountToDeposit)
            throws AccountNotFoundException;
}
