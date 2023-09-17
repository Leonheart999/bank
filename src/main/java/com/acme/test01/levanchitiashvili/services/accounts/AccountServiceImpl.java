package com.acme.test01.levanchitiashvili.services.accounts;

import com.acme.test01.levanchitiashvili.exceptions.AccountNotFoundException;
import com.acme.test01.levanchitiashvili.exceptions.WithdrawalAmountTooLargeException;
import com.acme.test01.levanchitiashvili.models.accounts.Account;
import com.acme.test01.levanchitiashvili.models.accounts.AccountDefault;
import com.acme.test01.levanchitiashvili.repositories.jpa.accounts.AccountJPARepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.descriptor.sql.internal.Scale6IntervalSecondDdlType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountJPARepository accountJPARepository;

    @Override
    public Account save(Account account) {
        return accountJPARepository.save(account);
    }

    @Override
    public void openSavingsAccount(BigDecimal amountToDeposit, String customerNum) {
        Account savings = new Account();
        savings.setBalance(amountToDeposit);
        savings.setCustomerNum(customerNum);
        savings.setType(Account.Type.SAVINGS);
        if (!checkSavingsValid(savings)) {
            throw new RuntimeException("savings account balance invalid");
        }
        save(savings);
    }

    @Override
    public void openCurrentAccount(BigDecimal overdraftAmount, String customerNum) {
        Account current = new Account();
        current.setOverdraftLimit(overdraftAmount);
        current.setBalance(BigDecimal.ZERO);
        current.setCustomerNum(customerNum);
        current.setType(Account.Type.CURRENT);
        if (!checkCurrentValid(current)) {
            throw new RuntimeException("current account overdraft limit invalid");
        }
        save(current);
    }

    @Override
    public Account get(Long id) throws AccountNotFoundException {
        Optional<Account> account = accountJPARepository.findById(id);
        if (account.isEmpty()) {
            throw new AccountNotFoundException();
        }
        return account.get();
    }


    @Override
    public void withdraw(Long accountId, BigDecimal amountToWithdraw) throws AccountNotFoundException, WithdrawalAmountTooLargeException {
        Account account = get(accountId);
        account.setBalance(account.getBalance().subtract(amountToWithdraw));
        if(account.getType().equals(Account.Type.CURRENT)){
            if(account.getBalance().compareTo(BigDecimal.ZERO)<0 && account.getBalance().multiply(BigDecimal.valueOf(-1)).compareTo(account.getOverdraftLimit())>0){
                throw new WithdrawalAmountTooLargeException();
            }
        }else{
            if(checkSavingsValid(account)){
                throw new WithdrawalAmountTooLargeException();
            }
        }
        save(account);
    }

    @Override
    public void deposit(Long accountId, BigDecimal amountToDeposit) throws AccountNotFoundException {
        Account account = get(accountId);
        account.setBalance(account.getBalance().add(amountToDeposit));
        save(account);
    }

    private boolean checkSavingsValid(Account account) {
        AccountDefault saving = accountJPARepository.findAccountDefaultsByType(Account.Type.SAVINGS);
        if (account.getBalance() == null || account.getBalance().compareTo(saving.getMinBalance()) < 0)
            return false;
        return true;
    }

    private boolean checkCurrentValid(Account account) {
        AccountDefault current = accountJPARepository.findAccountDefaultsByType(Account.Type.CURRENT);
        if (account.getBalance() == null || account.getOverdraftLimit().compareTo(current.getMinBalance().multiply(BigDecimal.valueOf(-1))) > 0)
            return false;
        return true;
    }

}
