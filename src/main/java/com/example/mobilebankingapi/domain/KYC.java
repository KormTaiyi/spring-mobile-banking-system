package com.example.mobilebankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// know your customer
@Getter
@Setter
@NoArgsConstructor
@Entity
public class KYC {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String nationalCardId;

    @Column(nullable = false)
    private Boolean isVerified;

    private Boolean isDeleted;

    @OneToOne
//    @MapsId
    @JoinColumn(name="cust_id")
    private Customer customer;
}
