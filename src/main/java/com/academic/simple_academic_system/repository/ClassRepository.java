package com.academic.simple_academic_system.repository;

import com.academic.simple_academic_system.model.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<Classes, UUID> {

    @Query(value = "select c.id from classes c where c.class_name = :classes", nativeQuery = true)
    UUID getClassIdByClassName(String classes);
}
