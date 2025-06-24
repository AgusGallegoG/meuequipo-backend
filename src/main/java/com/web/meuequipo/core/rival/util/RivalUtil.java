package com.web.meuequipo.core.rival.util;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.image.util.ImageUtil;
import com.web.meuequipo.core.rival.Rival;
import com.web.meuequipo.core.rival.dto.response.ResponseRivalDetails;
import com.web.meuequipo.core.rival.dto.response.ResponseRivalItem;
import com.web.meuequipo.core.shared.dto.response.ResponseMatchTeam;

public class RivalUtil {

    public static ResponseRivalItem mapRivalToResponseRivalItem(Rival rival) {
        ResponseRivalItem responseRivalItem = new ResponseRivalItem();

        responseRivalItem.setId(rival.getId());
        responseRivalItem.setName(rival.getName());
        responseRivalItem.setResponsible(rival.getResponsible());
        responseRivalItem.setTlf(rival.getTlf());

        if (rival.getLogo() != null) {
            responseRivalItem.setLogo(ImageUtil.getImageViewDTO(rival.getLogo()));
        }

        return responseRivalItem;
    }

    public static ResponseRivalDetails mapRivalToResponseRivalDetails(Rival rival) {
        ResponseRivalDetails responseRivalDetails = new ResponseRivalDetails();

        responseRivalDetails.setId(rival.getId());
        responseRivalDetails.setName(rival.getName());
        responseRivalDetails.setResponsible(rival.getResponsible());
        responseRivalDetails.setTlf(rival.getTlf());
        responseRivalDetails.setEmail(rival.getEmail());
        responseRivalDetails.setCategories(rival.getCategories().stream()
                .map(Category::getId).toList());

        if (rival.getLogo() != null) {
            responseRivalDetails.setLogo(ImageUtil.getImageViewDTO(rival.getLogo()));
        }

        return responseRivalDetails;
    }

    public static ResponseMatchTeam mapRivalToResponseMatchTeam(Rival rival) {
        ResponseMatchTeam responseMatchTeam = new ResponseMatchTeam();

        responseMatchTeam.setId(rival.getId());
        responseMatchTeam.setName(rival.getName());
        responseMatchTeam.setIsOurTeam(false);
        if (rival.getLogo() != null) {
            responseMatchTeam.setLogo(ImageUtil.getImageViewDTO(rival.getLogo()));
        }
        return responseMatchTeam;
    }

}
