package com.academic.simple_academic_system.util;

import com.academic.simple_academic_system.model.request.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

@Slf4j
public final class PageableUtil {

    public static <T> Pagination pageToPagination(Page<T> page) {
        return Pagination.builder()
                .pageSize(page.getSize())

                //paging in jpa starts from 0, so need to plus one for back to user page number
                .currentPage(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalRecords(page.getTotalElements())
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .build();
    }
}
