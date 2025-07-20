package com.example.demo.controller;

import com.example.demo.Service.EquityService;
import com.example.demo.dto.CreateEquityRequest;
import com.example.demo.dto.EquityResponse;
import com.example.demo.dto.PaginatedResponse;
import com.example.demo.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equities")
@RequiredArgsConstructor
@Tag(name = "Equity", description = "Equity management APIs")
public class EquityController {

    private final EquityService equityService;

    @GetMapping
    @Operation(summary = "Get equities list", description = "Returns the list of equities")
    public ResponseEntity<Response<PaginatedResponse<EquityResponse>>> getEquities(
            @RequestParam(defaultValue = "1") int page,   // 1-based page index
            @RequestParam(defaultValue = "10") int size,
            @Parameter(hidden = true) Sort sort  // Pulls sort query string, e.g., ?sort=name,asc
    ) {
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        PaginatedResponse<EquityResponse> equities = equityService.getEquities(pageable);
        return ResponseEntity.ok(Response.success("Equities fetched successfully", equities));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get equity by ID", description = "Returns a single equity")
    public ResponseEntity<Response<EquityResponse>> getEquityById(@PathVariable Long id) {
        EquityResponse equityResponse = equityService.getEquityById(id);
        return ResponseEntity.ok(Response.success(equityResponse));
    }

    @PostMapping("/createEquity")
    @Operation(summary = "Create a new equity", description = "Creates a new equity and returns the equity")
    public ResponseEntity<Response<EquityResponse>> createEquity(@Valid @RequestBody CreateEquityRequest request) {
        EquityResponse equityResponse = equityService.createEquity(request);
        return ResponseEntity.ok(Response.success(equityResponse));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete equity by ID", description = "Deletes a single equity")
    public ResponseEntity<Response<String>> deleteByID(@PathVariable Long id) {
        equityService.deleteEquityById(id);
        return ResponseEntity.ok(Response.success("Deleted successfully"));
    }
}
