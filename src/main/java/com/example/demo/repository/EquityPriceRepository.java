package com.example.demo.repository;

import com.example.demo.model.EquityPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquityPriceRepository extends JpaRepository<EquityPrice, Long> {}