package com.example.demo.database.seed;

import com.example.demo.model.Equity;
import com.example.demo.model.EquityPrice;
import com.example.demo.repository.EquityPriceRepository;
import com.example.demo.repository.EquityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Order(2)
public class EquityPriceSeeder implements CommandLineRunner {

    private final EquityRepository equityRepository;
    private final EquityPriceRepository equityPriceRepository;

    private final Random random = new Random();

    @Override
    public void run(String... args) {
        if (equityPriceRepository.count() == 0) {
            List<Equity> equities = equityRepository.findAll();

            List<EquityPrice> allPrices = new ArrayList<>();

            for (Equity equity : equities) {
                for (int i = 0; i < 100; i++) {
                    LocalDate date = LocalDate.now().minusDays(i);
                    double open = 100 + random.nextDouble() * 50;
                    double close = open + random.nextDouble() * 5 - 2.5;
                    double high = Math.max(open, close) + random.nextDouble() * 3;
                    double low = Math.min(open, close) - random.nextDouble() * 3;
                    long volume = 100000 + random.nextInt(1000000);

                    EquityPrice price = new EquityPrice();
                    price.setEquity(equity);
                    price.setDate(date);
                    price.setOpenPrice(round(open));
                    price.setClosePrice(round(close));
                    price.setHighPrice(round(high));
                    price.setLowPrice(round(low));
                    price.setVolume(volume);
                    price.setAdjustedClose(round(close)); // Assuming adjusted close is same here

                    allPrices.add(price);
                }
            }

            equityPriceRepository.saveAll(allPrices);
            System.out.println("✅ Seeded " + allPrices.size() + " equity prices.");
        }
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
