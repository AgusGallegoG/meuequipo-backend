package com.web.meuequipo.core.team.dto.response;

import com.web.meuequipo.core.shared.dto.response.SelectDTO;

import java.util.List;

public record TeamMenuItemResponse(
        Long id,
        String name,
        List<SelectDTO> teams
) {
}
