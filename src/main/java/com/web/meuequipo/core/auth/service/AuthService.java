package com.web.meuequipo.core.auth.service;

import com.web.meuequipo.core.auth.dto.request.LoginRequest;
import com.web.meuequipo.core.auth.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse authenticate(LoginRequest loginRequest);


}
