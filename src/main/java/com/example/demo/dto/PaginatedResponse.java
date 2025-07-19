package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponse<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    private boolean hasMore;
    private boolean hasPrevious;

    public PaginatedResponse(Page<T> dtoPage) {
        this.content = dtoPage.getContent();
        this.pageNumber = dtoPage.getNumber() + 1;
        this.pageSize = dtoPage.getSize();
        this.totalElements = dtoPage.getTotalElements();
        this.totalPages = dtoPage.getTotalPages();
        this.first = dtoPage.isFirst();
        this.last = dtoPage.isLast();
        this.hasMore = dtoPage.hasNext();
        this.hasPrevious = dtoPage.hasPrevious();
    }
}
