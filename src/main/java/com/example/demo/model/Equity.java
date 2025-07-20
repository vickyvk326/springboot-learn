package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String symbol; // e.g., AAPL, TSLA

    private String name;   // e.g., Apple Inc.
    private String exchange; // e.g., NASDAQ

    private String sector;   // e.g., Technology
    private String industry; // e.g., Consumer Electronics

    private String currency; // e.g., USD

    @OneToMany(mappedBy = "equity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquityPrice> prices = new ArrayList<>();
}
