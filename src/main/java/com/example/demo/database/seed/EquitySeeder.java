package com.example.demo.database.seed;

import com.example.demo.model.Equity;
import com.example.demo.repository.EquityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class EquitySeeder implements CommandLineRunner {

    private final EquityRepository equityRepository;

    @Override
    public void run(String... args) {
        if (equityRepository.count() == 0) {
            System.out.println("Seeding EquityRepository");
            List<Equity> equities = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                Equity equity = new Equity();
                equity.setSymbol("EQ" + i);
                equity.setName("Equity " + i);
                equity.setExchange(i % 2 == 0 ? "NASDAQ" : "NYSE");
                equity.setSector("Sector " + (i % 5));
                equity.setIndustry("Industry " + (i % 10));
                equity.setCurrency("USD");
                equities.add(equity);
            }
            equityRepository.saveAll(equities);
            System.out.println("✅ Seeded 100 equities.");
        }
    }
}
