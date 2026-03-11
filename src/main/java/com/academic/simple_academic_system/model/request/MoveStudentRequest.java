package com.academic.simple_academic_system.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveStudentRequest extends BaseRequest{
    private String studentId;
    private String classes;
}
