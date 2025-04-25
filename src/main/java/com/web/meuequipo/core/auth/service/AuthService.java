package com.web.meuequipo.core.auth.service;

import com.web.meuequipo.core.auth.dto.LoginRequest;
import com.web.meuequipo.core.auth.dto.LoginResponse;

public interface AuthService {

    LoginResponse authenticate(LoginRequest loginRequest);


}
