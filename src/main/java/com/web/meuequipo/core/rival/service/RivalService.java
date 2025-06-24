package com.web.meuequipo.core.rival.service;

import com.web.meuequipo.core.rival.dto.request.RequestSaveRival;
import com.web.meuequipo.core.rival.dto.response.ResponseRivalDetails;
import com.web.meuequipo.core.rival.dto.response.ResponseRivalItem;
import com.web.meuequipo.core.shared.dto.response.ResponseMatchTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RivalService {
    ResponseRivalDetails getRivalDetails(Long id);

    Page<ResponseRivalItem> getRivalsTable(Pageable pageable);

    ResponseRivalItem saveRivalItem(RequestSaveRival requestSaveRival);

    List<ResponseMatchTeam> getRivalsByCategory(Long categoryId);
}
