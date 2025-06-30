package com.web.meuequipo.core.season.service;

import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.data.SeasonRepository;
import com.web.meuequipo.core.season.dto.request.SeasonCreateRequest;
import com.web.meuequipo.core.season.dto.response.SeasonResponse;
import com.web.meuequipo.core.season.exception.SeasonException;
import com.web.meuequipo.core.season.util.SeasonUtil;
import com.web.meuequipo.core.signinPeriod.SigninPeriod;
import com.web.meuequipo.core.signinPeriod.data.SigninPeriodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;

    private final SigninPeriodRepository signinPeriodRepository;

    public SeasonServiceImpl(SeasonRepository seasonRepository, SigninPeriodRepository signinPeriodRepository) {
        this.seasonRepository = seasonRepository;
        this.signinPeriodRepository = signinPeriodRepository;
    }

    @Override
    public SeasonResponse getActiveSeason() {
        Season active = seasonRepository.findByIsActiveTrue()
                .orElseThrow(() -> new SeasonException("No se encontró una temporada activa"));
        return SeasonUtil.createSeasonResponse(active);
    }

    @Override
    public List<SeasonResponse> getAllSeasons() {
        List<Season> seasons = seasonRepository.findAll();

        return seasons.stream().map(SeasonUtil::createSeasonResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SeasonResponse activateSeason(Long id) {
        List<Season> allSeasons = seasonRepository.findAll();
        for (Season season : allSeasons) {
            season.setIsActive(season.getId().equals(id)); // Si es el id del paramtro, se activa, si no se desactiva
        }
        seasonRepository.saveAll(allSeasons);
        Season activeSeason = seasonRepository.findByIsActiveTrue()
                .orElseThrow(() -> new SeasonException("No se encontró una temporada activa"));

        return SeasonUtil.createSeasonResponse(activeSeason);
    }

    @Override
    @Transactional
    public SeasonResponse createSeason(SeasonCreateRequest seasonCreateRequest) {
        Season newSeason = SeasonUtil.createSeason(seasonCreateRequest);

        seasonRepository.save(newSeason);

        this.createSigninPeriod(newSeason);

        if (seasonCreateRequest.getActive()) {
            return activateSeason(newSeason.getId());
        } else {
            return SeasonUtil.createSeasonResponse(newSeason);
        }
    }

    private void createSigninPeriod(Season season) {
        SigninPeriod signinPeriod = new SigninPeriod();

        signinPeriod.setSeason(season);
        signinPeriod.setDateInit(LocalDate.now());
        signinPeriod.setDateEnd(LocalDate.now().plusMonths(1));
        signinPeriod.setDownloads(0);


        signinPeriodRepository.save(signinPeriod);
    }
}
