package com.example.mobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "account_types")
public class AccountType {
    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String name;    // Savings, Current

    @OneToMany(mappedBy = "accountType")
    private List<Account> accounts;
}
