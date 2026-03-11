package com.academic.simple_academic_system.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Students {
    @Id
    private UUID id;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
    @Column(nullable = false)
    private String name;
    @Column(name = "class_id", nullable = false)
    private UUID classId;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String nisn;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
}
