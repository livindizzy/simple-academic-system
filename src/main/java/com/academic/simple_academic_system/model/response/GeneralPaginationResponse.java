package com.academic.simple_academic_system.model.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GeneralPaginationResponse<T> extends BasePaginationResponse {
    private List<T> contents;
}
