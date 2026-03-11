package com.academic.simple_academic_system.service;

import com.academic.simple_academic_system.exception.BadRequestException;
import com.academic.simple_academic_system.model.entity.Students;
import com.academic.simple_academic_system.model.request.MoveStudentRequest;
import com.academic.simple_academic_system.model.response.ValidationResponse;
import com.academic.simple_academic_system.repository.ClassRepository;
import com.academic.simple_academic_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoveStudentService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;

    @CacheEvict(value = "students", allEntries = true)
    public ValidationResponse execute(MoveStudentRequest request){
        log.info("Start to move student service");
        Students students = studentRepository.findById(UUID.fromString(request.getStudentId()))
                .orElseThrow(() -> new BadRequestException("Data murid tidak di temukan"));
        UUID classId = classRepository.getClassIdByClassName(request.getClasses());
        if (ObjectUtils.isEmpty(classId)) throw new BadRequestException("Kelas tidak di temukan");
        log.info("Move student with id {} to class {}", request.getStudentId(), request.getClasses());
        students.setClassId(classId);
        students.setModifiedDate(LocalDateTime.now());
        students.setModifiedBy(request.getUsername());
        studentRepository.save(students);
        log.info("End of move student service");
        return ValidationResponse.builder().result(true).build();
    }
}
