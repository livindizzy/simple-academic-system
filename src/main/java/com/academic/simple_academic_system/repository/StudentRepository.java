package com.academic.simple_academic_system.repository;

import com.academic.simple_academic_system.model.entity.Students;
import com.academic.simple_academic_system.model.projection.GetStudentView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Students, UUID> {

    @Query(value = """
            SELECT s.id AS id, s.name AS name, s.nisn AS nisn, c.class_name AS className
            FROM students s
            LEFT JOIN classes c ON c.id = s.class_id
            WHERE (
                :search IS NULL
                OR lower(s.name) LIKE lower('%' || :search || '%')
                OR lower(s.nisn) LIKE lower('%' || :search || '%')
                OR lower(c.class_name) LIKE lower('%' || :search || '%')
            )
            """, nativeQuery = true,
    countQuery = """
            SELECT count(1)
            FROM students s
            LEFT JOIN classes c ON c.id = s.class_id
            WHERE (
                :search IS NULL
                OR lower(s.name) LIKE lower('%' || :search || '%')
                OR lower(s.nisn) LIKE lower('%' || :search || '%')
                OR lower(c.class_name) LIKE lower('%' || :search || '%')
            )
            """)
    Page<GetStudentView> getStudentList(Pageable pageable, String search);

    @Query(value = """
    SELECT s.name AS name, s.nisn AS nisn, c.class_name AS className, s.date_of_birth as dateOfBirth
    FROM students s
    LEFT JOIN classes c ON c.id = s.class_id
    """, nativeQuery = true)
    List<GetStudentView> downloadStudentData();
}
