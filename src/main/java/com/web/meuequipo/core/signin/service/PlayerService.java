package com.web.meuequipo.core.signin.service;

import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import com.web.meuequipo.core.signin.dto.request.SigninRequest;
import com.web.meuequipo.core.signin.dto.response.SigninItemResponse;
import com.web.meuequipo.core.signin.dto.response.SigninResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerService {

    List<SelectDTO> getFreePlayersByCategory(Long categoryId);

    void savePublicSignin(SigninRequest signinRequest);

    SigninItemResponse saveAdminSignin(SigninRequest signinRequest);

    Page<SigninItemResponse> getSigninItemPage(Pageable pageable, Long categoryId, Long signinState);

    SigninResponse getSigninById(Long id);
}
