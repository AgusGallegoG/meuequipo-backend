package com.web.meuequipo.core.season.service;

import com.web.meuequipo.core.season.dto.request.SeasonCreateRequest;
import com.web.meuequipo.core.season.dto.response.SeasonResponse;

import java.util.List;

public interface SeasonService {
    SeasonResponse getActiveSeason();

    List<SeasonResponse> getAllSeasons();

    List<SeasonResponse> activateSeason(Long id);

    List<SeasonResponse> createSeason(SeasonCreateRequest seasonCreateRequest);
}
