package com.academic.simple_academic_system.service;

import com.academic.simple_academic_system.model.request.BaseRequest;
import com.academic.simple_academic_system.model.response.BaseResponse;

public interface BaseService<T extends BaseRequest, V extends BaseResponse> {
    V execute(T input);
}
