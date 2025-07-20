package com.example.demo.mapper;

import com.example.demo.dto.CreateEquityRequest;
import com.example.demo.dto.EquityResponse;
import com.example.demo.model.Equity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquityMapper {

    // Dto to Equity
    Equity toEquity(CreateEquityRequest dto);

    // Equity to Dto
    EquityResponse toDto(Equity equity);
}
