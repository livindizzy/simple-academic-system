package com.academic.simple_academic_system.service;

import com.academic.simple_academic_system.model.projection.GetStudentView;
import com.academic.simple_academic_system.model.request.BasePaginationRequest;
import com.academic.simple_academic_system.model.request.BaseSearchRequest;
import com.academic.simple_academic_system.model.response.GeneralPaginationResponse;
import com.academic.simple_academic_system.model.response.StudentResponse;
import com.academic.simple_academic_system.repository.StudentRepository;
import com.academic.simple_academic_system.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InquiryStudentService extends BasePaginationService<BaseSearchRequest, GeneralPaginationResponse<StudentResponse>> {

    private final StudentRepository studentRepository;

    @Override
    @Cacheable(value = "students", key = "#request.toString()")
    public GeneralPaginationResponse<StudentResponse> execute(BaseSearchRequest request) {
        log.info("Start InquiryStudentService");
        Page<GetStudentView> pagination = pagination(request);
        List<StudentResponse> list = pagination.getContent().stream().map(data -> StudentResponse.builder()
                .studentId(String.valueOf(data.getId()))
                .studentName(data.getName())
                .nisn(data.getNisn())
                .classes(data.getClassName())
                .build()).toList();
        GeneralPaginationResponse<StudentResponse> response = GeneralPaginationResponse.<StudentResponse>builder()
                .contents(list)
                .build();
        response.setPagination(PageableUtil.pageToPagination(pagination));
        log.info("End of InquiryStudentService");
        return response;
    }

    private Page<GetStudentView> pagination(BaseSearchRequest input) {
        input.setSortBy("className");
        Pageable pageRequest = unsafePaging(input, getPageSize(), getPageNumber());
        return studentRepository.getStudentList(pageRequest, input.getSearch());
    }

    public static <T extends BasePaginationRequest> Pageable unsafePaging(T input, Integer defaultPageSize, Integer defaultPageNumber) {
        Sort.Direction order = "DESC".equalsIgnoreCase(input.getSortType()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        JpaSort sort = JpaSort.unsafe(order, input.getSortBy());
        Integer pageSize = input.getPageSize() != null ? input.getPageSize() : defaultPageSize;
        return PageRequest.of(getPageNumber(input.getPageNumber(), defaultPageNumber), pageSize, sort);
    }

    public static Integer getPageNumber(Integer reqPageNumber, Integer defaultPageNumber) {
        if (reqPageNumber != null) {
            if (reqPageNumber > 0) {
                return reqPageNumber - 1;
            } else {
                log.warn("Page index must not be less than one original value {}, default value {} is used instead.", reqPageNumber, defaultPageNumber + 1);
            }
        }
        return defaultPageNumber;
    }


}
