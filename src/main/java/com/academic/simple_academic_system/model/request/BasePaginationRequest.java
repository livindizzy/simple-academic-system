package com.academic.simple_academic_system.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BasePaginationRequest extends BaseRequest {

    private static final long serialVersionUID = -1088344338839064492L;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("page_number")
    private Integer pageNumber;

    @JsonProperty("sort_by")
    private String sortBy;

    @JsonProperty("sort_type")
    private String sortType;

}
