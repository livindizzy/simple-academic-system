package com.academic.simple_academic_system.service;

import com.academic.simple_academic_system.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetDownloadResultService {

    public static ResponseEntity<byte[]> execute(String requestId) {
        log.info("Start to get download result with requetsId: {}", requestId);
        String fileName = "students_" + requestId + ".xlsx";
        Path path = Paths.get("downloads/" + fileName);
        if (!Files.exists(path)) {
            throw new BadRequestException("File belum siap, silahkan coba beberapa saat lagi");
        }
        try {
            byte[] fileContent = Files.readAllBytes(path);
            log.info("End of proccess to get download result");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(fileContent);
        } catch (IOException e) {
            throw new BadRequestException("Gagal membaca file: " + e.getMessage());
        }
    }

}