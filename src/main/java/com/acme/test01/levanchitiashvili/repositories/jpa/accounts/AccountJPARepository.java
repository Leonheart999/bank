package com.acme.test01.levanchitiashvili.repositories.jpa.accounts;


import com.acme.test01.levanchitiashvili.config.BaseRepository;
import com.acme.test01.levanchitiashvili.models.accounts.Account;
import com.acme.test01.levanchitiashvili.models.accounts.AccountDefault;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJPARepository extends BaseRepository<Account, Long> {

    @Query("select ad from AccountDefault ad where ad.type=:type")
    AccountDefault findAccountDefaultsByType(Account.Type type);
}
