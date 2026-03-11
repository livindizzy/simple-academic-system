package com.academic.simple_academic_system.controller;

import com.academic.simple_academic_system.model.request.BaseSearchRequest;
import com.academic.simple_academic_system.model.request.CreateStudentRequest;
import com.academic.simple_academic_system.model.request.MoveStudentRequest;
import com.academic.simple_academic_system.model.response.GeneralPaginationResponse;
import com.academic.simple_academic_system.model.response.Response;
import com.academic.simple_academic_system.model.response.StudentResponse;
import com.academic.simple_academic_system.model.response.ValidationResponse;
import com.academic.simple_academic_system.service.*;
import com.academic.simple_academic_system.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final CreateStudentService createStudentService;
    private final InquiryStudentService inquiryStudentService;
    private final MoveStudentService moveStudentService;
    private final DownloadStudentProducerService downloadStudentProducerService;
    private final GetDownloadResultService getDownloadResultService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "Create student data API", description = "API to create student data")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Welcome to system message"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "500", description = "Something went wrong") })
    @PostMapping("/v1/create")
    public Response<ValidationResponse> createStudent(@RequestHeader("Authorization") String token,
                                                      @RequestBody CreateStudentRequest request) {
        jwtUtil.extractJwt(token, request);
        return Response.success(createStudentService.execute(request),"Success");
    }

    @Operation(summary = "Get list student data API", description = "API to get list student data")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Welcome to system message"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "500", description = "Something went wrong") })
    @GetMapping("/v1/list")
    public Response<GeneralPaginationResponse<StudentResponse>> inquiryDataStudent(@RequestHeader("Authorization") String token,
                                                                                   BaseSearchRequest request) {
        jwtUtil.extractJwt(token, request);
        return Response.success(inquiryStudentService.execute(request), "Get list student");
    }

    @Operation(summary = "Move student data API", description = "API to move student data")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Welcome to system message"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "500", description = "Something went wrong") })
    @PostMapping("/v1/move")
    public Response<ValidationResponse> moveStudent(@RequestHeader("Authorization") String token,
                                                    @RequestBody MoveStudentRequest request) {
        jwtUtil.extractJwt(token, request);
        return Response.success(moveStudentService.execute(request),"Success");
    }

    @Operation(summary = "Download student data API", description = "API to Download student data")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Welcome to system message"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "500", description = "Something went wrong") })
    @PostMapping("/v1/download")
    public Response<String> downloadStudentData(@RequestHeader("Authorization") String token) {
        String requestId = UUID.randomUUID().toString().replace("-", "").substring(0, 5).toUpperCase();
        downloadStudentProducerService.sendDownloadRequest(requestId);
        return Response.success(requestId, "Download is being processed");
    }

    @GetMapping("v1/download/result")
    public ResponseEntity<byte[]> downloadResult(@RequestParam String requestId) {
        return getDownloadResultService.execute(requestId);
    }
}
