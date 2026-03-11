package com.academic.simple_academic_system.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Pagination implements Serializable {
    private Integer pageSize;
    private Integer currentPage;
    private Integer totalPages;
    private Long totalRecords;
    private Boolean isFirstPage;
    private Boolean isLastPage;
}
