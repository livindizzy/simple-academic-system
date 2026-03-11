package com.academic.simple_academic_system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DownloadStudentProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendDownloadRequest(String requestId) {
        log.info("Sending download request to Kafka with requestId: {}", requestId);
        kafkaTemplate.send("student-download", requestId);
    }
}
