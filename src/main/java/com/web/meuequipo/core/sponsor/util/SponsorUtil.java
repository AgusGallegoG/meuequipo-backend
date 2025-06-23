package com.web.meuequipo.core.sponsor.util;

import com.web.meuequipo.core.image.util.ImageUtil;
import com.web.meuequipo.core.sponsor.Sponsor;
import com.web.meuequipo.core.sponsor.dto.response.SponsorResponse;

import java.util.Objects;

public class SponsorUtil {

    public static SponsorResponse sponsorToSponsorResponse(Sponsor sponsor) {
        SponsorResponse sponsorResponse = new SponsorResponse();

        sponsorResponse.setId(sponsor.getId());
        sponsorResponse.setName(sponsor.getName());
        sponsorResponse.setUrl(sponsor.getUrl());
        if (!Objects.isNull(sponsor.getLogo())) {
            sponsorResponse.setLogo(ImageUtil.getImageViewDTO(sponsor.getLogo()));
        }

        return sponsorResponse;
    }
}
