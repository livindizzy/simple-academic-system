package com.academic.simple_academic_system.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BaseSearchRequest extends BasePaginationRequest {

    @Schema(defaultValue="Common Text Search")
    private String search;

    @Schema(defaultValue="Status")
    private Integer status;

    @Schema(defaultValue="Deleted Status")
    private Integer isDeleted;

    @Schema(defaultValue="Start Date")
    private String startDate;

    @Schema(defaultValue="End Date")
    private String endDate;

}
