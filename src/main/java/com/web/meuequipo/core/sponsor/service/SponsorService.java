package com.web.meuequipo.core.sponsor.service;

import com.web.meuequipo.core.sponsor.dto.request.SponsorSaveRequest;
import com.web.meuequipo.core.sponsor.dto.response.SponsorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SponsorService {
    List<SponsorResponse> getSponsorFooter();

    Page<SponsorResponse> getSponsorsTable(Pageable pageable);

    SponsorResponse saveSponsor(SponsorSaveRequest sponsorSaveRequest);

}
