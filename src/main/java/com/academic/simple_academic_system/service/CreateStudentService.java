package com.academic.simple_academic_system.service;

import com.academic.simple_academic_system.exception.BadRequestException;
import com.academic.simple_academic_system.model.entity.Students;
import com.academic.simple_academic_system.model.request.CreateStudentRequest;
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
public class CreateStudentService {
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;

    @CacheEvict(value = "students", allEntries = true)
    public ValidationResponse execute(CreateStudentRequest request){
        log.info("Start to create Student: {}", request);
        LocalDateTime localDateTime = LocalDateTime.now();
        UUID classes = classRepository.getClassIdByClassName(request.getClasses());
        if (ObjectUtils.isEmpty(classes)) throw new BadRequestException("Kelas tidak di temukan");
        Students students = new Students();
        students.setId(UUID.randomUUID());
        students.setCreatedBy(request.getUsername());
        students.setCreatedDate(localDateTime);
        students.setModifiedBy(request.getUsername());
        students.setModifiedDate(LocalDateTime.now());
        students.setName(request.getName());
        students.setGender(request.getGender());
        students.setClassId(classes);
        students.setNisn(request.getNisn());
        students.setDateOfBirth(request.getDateOfBirth());
        studentRepository.save(students);
        log.info("End of CreateStudentService");

        return ValidationResponse.builder().result(true).build();
    }
}
