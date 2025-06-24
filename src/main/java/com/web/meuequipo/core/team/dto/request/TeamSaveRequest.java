package com.web.meuequipo.core.team.dto.request;

import com.web.meuequipo.core.image.dto.ImageViewDTO;
import lombok.Data;

import java.util.List;

@Data
public class TeamSaveRequest {
    private Long id;
    private String name;
    private Long category;
    private Long sex;
    private String trainer;
    private String trainerContact;
    private ImageViewDTO teamImage;
    private List<Long> players;
}
