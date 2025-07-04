package com.web.meuequipo.core.sponsor.service;

import com.web.meuequipo.core.image.Image;
import com.web.meuequipo.core.image.data.ImageRepository;
import com.web.meuequipo.core.image.exception.ImageException;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.data.SeasonRepository;
import com.web.meuequipo.core.season.exception.SeasonException;
import com.web.meuequipo.core.sponsor.Sponsor;
import com.web.meuequipo.core.sponsor.data.SponsorRepository;
import com.web.meuequipo.core.sponsor.dto.request.SponsorSaveRequest;
import com.web.meuequipo.core.sponsor.dto.response.SponsorResponse;
import com.web.meuequipo.core.sponsor.exception.SponsorException;
import com.web.meuequipo.core.sponsor.util.SponsorUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class SponsorServiceImpl implements SponsorService {

    private final SponsorRepository sponsorRepository;

    private final ImageRepository imageRepository;

    private final SeasonRepository seasonRepository;

    public SponsorServiceImpl(SponsorRepository sponsorRepository, ImageRepository imageRepository, SeasonRepository seasonRepository) {
        this.sponsorRepository = sponsorRepository;
        this.imageRepository = imageRepository;
        this.seasonRepository = seasonRepository;
    }

    @Override
    public List<SponsorResponse> getSponsorFooter() {
        List<Sponsor> footer = this.sponsorRepository.findAllOfActualSeason();

        return footer.stream().map(SponsorUtil::sponsorToSponsorResponse).toList();
    }

    @Override
    public Page<SponsorResponse> getSponsorsTable(Pageable pageable) {
        Page<Sponsor> table = this.sponsorRepository.findAllOfActualSeason(pageable);

        return table.map(SponsorUtil::sponsorToSponsorResponse);
    }


    @Override
    @Transactional
    public SponsorResponse saveSponsor(SponsorSaveRequest sponsorSaveRequest) {
        Sponsor saved;
        if (sponsorSaveRequest.getId() != null) {
            saved = this.updateSponsor(sponsorSaveRequest);
        } else {
            saved = this.createSponsor(sponsorSaveRequest);
        }

        return SponsorUtil.sponsorToSponsorResponse(saved);
    }

    private Sponsor updateSponsor(SponsorSaveRequest sponsorSaveRequest) {
        Sponsor sponsor = sponsorRepository.findById(sponsorSaveRequest.getId())
                .orElseThrow(() -> new SponsorException("Non se atopou o sponsor co id: " + sponsorSaveRequest.getId()));

        return saveSponsorEntity(sponsorSaveRequest, sponsor);
    }

    private Sponsor createSponsor(SponsorSaveRequest sponsorSaveRequest) {
        Sponsor sponsor = new Sponsor();

        sponsor.setSeason(this.getActiveSeason());

        return saveSponsorEntity(sponsorSaveRequest, sponsor);
    }

    private Sponsor saveSponsorEntity(SponsorSaveRequest sponsorSaveRequest, Sponsor sponsor) {
        sponsor.setName(sponsorSaveRequest.getName());
        sponsor.setUrl(sponsorSaveRequest.getUrl());
        if (sponsorSaveRequest.getLogo() != null && sponsorSaveRequest.getLogo().getId() != null) {
            sponsor.setLogo(this.getImage(sponsorSaveRequest.getLogo().getId()));
        }

        return this.sponsorRepository.save(sponsor);
    }

    private Season getActiveSeason() {
        return seasonRepository.findByIsActiveTrue()
                .orElseThrow(() -> new SeasonException("Non se atopou Season activa"));
    }

    private Image getImage(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ImageException("Non se atopou imaxe co id" + id));
    }

}
