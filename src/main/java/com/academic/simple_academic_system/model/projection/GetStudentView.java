package com.academic.simple_academic_system.model.projection;

import java.time.LocalDate;
import java.util.UUID;

public interface GetStudentView {
    UUID getId();
    String getName();
    String getNisn();
    String getClassName();
    LocalDate getDateOfBirth();
}
