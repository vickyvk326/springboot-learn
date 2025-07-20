package com.example.demo.repository;

import com.example.demo.model.Equity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquityRepository extends JpaRepository<Equity, Long> {}