package com.acme.test01.levanchitiashvili.models.accounts;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
@Table(name = "accounts")
@SequenceGenerator(name = "accountIdSeq", sequenceName = "accounts_id_seq", allocationSize = 1)
public class Account {
    public enum Type{
        SAVINGS,CURRENT
    }
    @Id
    @GeneratedValue(generator = "accountIdSeq",strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
    @Column(name = "customer_num")
    private String customerNum;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "overdraft_limit")
    private BigDecimal overdraftLimit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

}
