package com.example.mobilebankingapi.doman;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="transaction_types")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class TransactionType {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

}
