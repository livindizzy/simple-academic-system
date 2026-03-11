package com.academic.simple_academic_system.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest extends BaseRequest{
    private String name;
    @NotBlank(message = "NISN harus di isi")
    @Pattern(regexp = "^[0-9]{10}$", message = "NISN harus 10 digit")
    private String nisn;
    private String gender;
    private LocalDate dateOfBirth;
    private String classes;
}
