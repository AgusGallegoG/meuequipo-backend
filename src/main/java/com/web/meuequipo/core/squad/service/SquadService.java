package com.web.meuequipo.core.squad.service;

import com.web.meuequipo.core.squad.dto.request.SquadCreateRequest;
import com.web.meuequipo.core.squad.dto.response.SquadResponse;

public interface SquadService {

    SquadResponse create(SquadCreateRequest request);
}
