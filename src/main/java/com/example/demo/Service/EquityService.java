package com.example.demo.Service;

import com.example.demo.dto.CreateEquityRequest;
import com.example.demo.dto.EquityResponse;
import com.example.demo.dto.PaginatedResponse;
import com.example.demo.mapper.EquityMapper;
import com.example.demo.model.Equity;
import com.example.demo.repository.EquityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EquityService {

    private final EquityRepository equityRepository;
    private final EquityMapper equityMapper;

    public PaginatedResponse<EquityResponse> getEquities(Pageable pageable) {
        Page<Equity> equityPage = equityRepository.findAll(pageable);
        Page<EquityResponse> equityResponsePage = equityPage.map(equityMapper::toDto);
        return new PaginatedResponse<>(equityResponsePage);
    }

    public EquityResponse getEquityById(Long id) {
        Equity equity = equityRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Equity not found"));
        return equityMapper.toDto(equity);
    }

    public EquityResponse createEquity(CreateEquityRequest equityRequest) {
        Equity newEquity = equityMapper.toEquity(equityRequest);
        return equityMapper.toDto(newEquity);
    }

    public void deleteEquityById(Long id) {
        if (!equityRepository.existsById(id)) throw new NoSuchElementException("Equity not found");
        equityRepository.deleteById(id);
    }
}
