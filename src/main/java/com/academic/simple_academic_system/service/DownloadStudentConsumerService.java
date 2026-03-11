package com.academic.simple_academic_system.service;

import com.academic.simple_academic_system.model.projection.GetStudentView;
import com.academic.simple_academic_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DownloadStudentConsumerService {

    private final StudentRepository studentRepository;

    @KafkaListener(topics = "student-download", groupId = "simple-academic-service")
    public void processDownload(String requestId) {
        log.info("Processing download request: {}", requestId);
        try {
            List<GetStudentView> students = studentRepository.downloadStudentData();
            byte[] excelFile = generateExcel(students);
            saveFile(excelFile, requestId);
            log.info("Download request {} completed", requestId);
        } catch (Exception e) {
            log.error("Error processing download request: {}", e.getMessage());
        }
    }

    private byte[] generateExcel(List<GetStudentView> students) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Students");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("No");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("NISN");
            headerRow.createCell(3).setCellValue("Class");
            headerRow.createCell(4).setCellValue("Date of Birth");
            int rowNum = 1;
            for (GetStudentView student : students) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rowNum);
                row.createCell(1).setCellValue(student.getName());
                row.createCell(2).setCellValue(student.getNisn());
                row.createCell(3).setCellValue(student.getClassName());
                row.createCell(4).setCellValue(student.getDateOfBirth().toString());
            }
            workbook.write(out);
            return out.toByteArray();
        }
    }

    private void saveFile(byte[] excelFile, String requestId) throws IOException {
        String fileName = "students_" + requestId + ".xlsx";
        Path path = Paths.get("downloads/" + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, excelFile);
        log.info("File saved: {}", fileName);
    }
}
