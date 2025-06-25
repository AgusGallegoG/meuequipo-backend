package com.web.meuequipo.core.squad.dto.response;

import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SquadResponse {
    private Long id;
    private LocalDateTime date;
    private String location;
    private List<SelectDTO> players;
}
