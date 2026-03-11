package com.academic.simple_academic_system.service;

import com.academic.simple_academic_system.model.request.BasePaginationRequest;
import com.academic.simple_academic_system.model.response.BasePaginationResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BasePaginationService<T extends BasePaginationRequest, V extends BasePaginationResponse> implements BaseService<T, V>{

    @Value("${default.page.size}")
    private Integer pageSize;

    @Value("${default.page.number}")
    private Integer pageNumber;

    @Value("${default.page.sortBy}")
    private String sortBy;

    @Value("${default.page.sortType}")
    private String sortType;
}
