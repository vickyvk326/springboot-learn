package com.example.demo.dto;

import com.example.demo.model.EquityPrice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class CreateEquityRequest {
    @NotBlank
    private String symbol; // e.g., AAPL, TSLA

    @NotBlank
    private String name;   // e.g., Apple Inc.

    @NotBlank
    private String exchange; // e.g., NASDAQ

    @NotBlank
    private String sector;   // e.g., Technology

    @NotBlank
    private String industry; // e.g., Consumer Electronics

    @NotBlank
    private String currency; // e.g., USD

    private List<EquityPrice> prices = new ArrayList<>();
}
