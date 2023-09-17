package com.acme.test01.levanchitiashvili.models.accounts;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "account_defaults")
public class AccountDefault {

    @Id
    @GeneratedValue(generator = "accountIdSeq",strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "min_balance", nullable = false)
    private BigDecimal minBalance;
    @Column(name = "max_balance")
    private BigDecimal maxBalance;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Account.Type type;
}
