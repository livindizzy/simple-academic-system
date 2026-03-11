package com.academic.simple_academic_system.model.response;

import com.academic.simple_academic_system.model.request.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BasePaginationResponse extends BaseResponse {
    private static final long serialVersionUID = 5578484318986645502L;

    private Pagination pagination;
}