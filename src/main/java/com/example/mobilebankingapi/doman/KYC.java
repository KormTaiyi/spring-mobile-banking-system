package com.example.mobilebankingapi.doman;

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
    private String id;
    private String nationalCardId;
    private Boolean isVerified;
    private Boolean isDeleted;

    @OneToOne
    @MapsId
    @JoinColumn(name="cust_id")
    private Customer customer;
}
