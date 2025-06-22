package com.web.meuequipo.core.season.util;

import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.dto.request.SeasonCreateRequest;
import com.web.meuequipo.core.season.dto.response.SeasonResponse;

import java.time.LocalDate;

public class SeasonUtil {
    private final static String SEASON_NAME = "Tempada";

    public static SeasonResponse createSeasonResponse(Season season) {
        SeasonResponse seasonResponse = new SeasonResponse();

        seasonResponse.setId(season.getId());
        seasonResponse.setName(season.getName());
        seasonResponse.setActive(season.getIsActive());

        return seasonResponse;
    }


    public static Season createSeason(SeasonCreateRequest seasonCreateRequest) {
        Season season = new Season();

        season.setEndDate(seasonCreateRequest.getEndDate());
        season.setStartDate(seasonCreateRequest.getStartDate());
        season.setIsActive(false);
        season.setName(generateNameSeason(seasonCreateRequest.getStartDate(), seasonCreateRequest.getEndDate()));

        return season;
    }

    private static String generateNameSeason(LocalDate startDate, LocalDate endDate) {
        return SEASON_NAME + ' ' + startDate.getYear() + ' ' + endDate.getYear();
    }
}
