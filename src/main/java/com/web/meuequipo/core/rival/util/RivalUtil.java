package com.web.meuequipo.core.rival.util;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.image.util.ImageUtil;
import com.web.meuequipo.core.rival.Rival;
import com.web.meuequipo.core.rival.dto.response.RivalDetailsResponse;
import com.web.meuequipo.core.rival.dto.response.RivalItemResponse;
import com.web.meuequipo.core.shared.dto.response.GameTeamDTO;

public class RivalUtil {

    public static RivalItemResponse mapRivalToResponseRivalItem(Rival rival) {
        RivalItemResponse rivalItemResponse = new RivalItemResponse();

        rivalItemResponse.setId(rival.getId());
        rivalItemResponse.setName(rival.getName());
        rivalItemResponse.setResponsible(rival.getResponsible());
        rivalItemResponse.setTlf(rival.getTlf());

        if (rival.getLogo() != null) {
            rivalItemResponse.setLogo(ImageUtil.getImageViewDTO(rival.getLogo()));
        }

        return rivalItemResponse;
    }

    public static RivalDetailsResponse mapRivalToResponseRivalDetails(Rival rival) {
        RivalDetailsResponse rivalDetailsResponse = new RivalDetailsResponse();

        rivalDetailsResponse.setId(rival.getId());
        rivalDetailsResponse.setName(rival.getName());
        rivalDetailsResponse.setResponsible(rival.getResponsible());
        rivalDetailsResponse.setTlf(rival.getTlf());
        rivalDetailsResponse.setEmail(rival.getEmail());
        rivalDetailsResponse.setCategories(rival.getCategories().stream()
                .map(Category::getId).toList());

        if (rival.getLogo() != null) {
            rivalDetailsResponse.setLogo(ImageUtil.getImageViewDTO(rival.getLogo()));
        }

        return rivalDetailsResponse;
    }

    public static GameTeamDTO mapRivalToResponseGameTeam(Rival rival) {
        GameTeamDTO gameTeamDTO = new GameTeamDTO();

        gameTeamDTO.setId(rival.getId());
        gameTeamDTO.setName(rival.getName());
        gameTeamDTO.setIsOurTeam(false);
        if (rival.getLogo() != null) {
            gameTeamDTO.setLogo(ImageUtil.getImageViewDTO(rival.getLogo()));
        }
        return gameTeamDTO;
    }

}
