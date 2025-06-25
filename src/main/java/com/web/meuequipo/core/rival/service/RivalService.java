package com.web.meuequipo.core.rival.service;

import com.web.meuequipo.core.rival.dto.request.RivalSaveRequest;
import com.web.meuequipo.core.rival.dto.response.RivalDetailsResponse;
import com.web.meuequipo.core.rival.dto.response.RivalItemResponse;
import com.web.meuequipo.core.shared.dto.response.GameTeamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RivalService {
    RivalDetailsResponse getRivalDetails(Long id);

    Page<RivalItemResponse> getRivalsTable(Pageable pageable);

    RivalItemResponse saveRivalItem(RivalSaveRequest rivalSaveRequest);

    List<GameTeamResponse> getRivalsByCategory(Long categoryId);
}
