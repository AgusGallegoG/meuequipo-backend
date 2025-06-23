package com.web.meuequipo.core.sponsor.service;

import com.web.meuequipo.core.image.Image;
import com.web.meuequipo.core.image.data.ImageRepository;
import com.web.meuequipo.core.image.exception.ImageException;
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

    public SponsorServiceImpl(SponsorRepository sponsorRepository, ImageRepository imageRepository) {
        this.sponsorRepository = sponsorRepository;
        this.imageRepository = imageRepository;
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

        return saveSponsorEntity(sponsorSaveRequest, sponsor);
    }

    private Sponsor saveSponsorEntity(SponsorSaveRequest sponsorSaveRequest, Sponsor sponsor) {
        sponsor.setName(sponsorSaveRequest.getName());
        sponsor.setUrl(sponsorSaveRequest.getUrl());
        if (sponsorSaveRequest.getLogo() != null && sponsorSaveRequest.getLogo().getId() != null) {
            Image image = imageRepository.findById(sponsorSaveRequest.getLogo().getId())
                    .orElseThrow(() -> new ImageException("Non se atopou imaxe co id" + sponsorSaveRequest.getLogo().getId()));
            sponsor.setLogo(image);
        }

        return this.sponsorRepository.save(sponsor);
    }
}
