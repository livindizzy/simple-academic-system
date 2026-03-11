package com.academic.simple_academic_system.repository;

import com.academic.simple_academic_system.model.entity.Scores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScoreRepository extends JpaRepository<Scores, UUID> {
}
