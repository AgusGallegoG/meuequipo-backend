package com.web.meuequipo.core.signinPeriod.service;

import com.web.meuequipo.core.signinPeriod.dto.request.SigninPeriodUpdateRequest;
import com.web.meuequipo.core.signinPeriod.dto.response.SigninPeriodInfoResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface SigninPeriodService {
    SigninPeriodInfoResponse updatePeriod(SigninPeriodUpdateRequest request);

    SigninPeriodInfoResponse updateForm(MultipartFile file);

    SigninPeriodInfoResponse getInfo();

    Resource downloadForm();
}
