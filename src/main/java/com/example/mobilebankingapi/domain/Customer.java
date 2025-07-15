package com.example.mobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="customers")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(length = 15)
    private String gender;

    @Column(nullable = false)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @OneToOne(mappedBy = "customer")
//    @JoinColumn(unique = true)
    private KYC kyc;

    @Column(unique = true, nullable = false)
    private String nationalId;

}
