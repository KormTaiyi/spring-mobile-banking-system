package com.example.mobilebankingapi.doman;

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
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

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
    private List<Accounts> accounts;

    @OneToOne(mappedBy = "customer")
    @PrimaryKeyJoinColumn
    private KYC kyc;

}
