package com.academic.simple_academic_system.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = 6824120659218678635L;

    private String role;
    private String username;

}
