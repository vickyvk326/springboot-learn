package com.example.demo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EquityResponse {
    private String symbol; // e.g., AAPL, TSLA

    private String name;   // e.g., Apple Inc.

    private String exchange; // e.g., NASDAQ

    private String sector;   // e.g., Technology

    private String industry; // e.g., Consumer Electronics

    private String currency; // e.g., USD
}
