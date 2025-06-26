package com.web.meuequipo.core.rival.service;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.category.data.CategoryRepository;
import com.web.meuequipo.core.image.Image;
import com.web.meuequipo.core.image.data.ImageRepository;
import com.web.meuequipo.core.image.exception.ImageException;
import com.web.meuequipo.core.rival.Rival;
import com.web.meuequipo.core.rival.data.RivalRepository;
import com.web.meuequipo.core.rival.dto.request.RivalSaveRequest;
import com.web.meuequipo.core.rival.dto.response.RivalDetailsResponse;
import com.web.meuequipo.core.rival.dto.response.RivalItemResponse;
import com.web.meuequipo.core.rival.exception.RivalException;
import com.web.meuequipo.core.rival.util.RivalUtil;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.data.SeasonRepository;
import com.web.meuequipo.core.season.exception.SeasonException;
import com.web.meuequipo.core.shared.dto.response.GameTeamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RivalServiceImpl implements RivalService {

    private final RivalRepository rivalRepository;

    private final ImageRepository imageRepository;

    private final SeasonRepository seasonRepository;

    private final CategoryRepository categoryRepository;

    public RivalServiceImpl(RivalRepository rivalRepository, ImageRepository imageRepository, SeasonRepository seasonRepository, CategoryRepository categoryRepository) {
        this.rivalRepository = rivalRepository;
        this.imageRepository = imageRepository;
        this.seasonRepository = seasonRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public RivalDetailsResponse getRivalDetails(Long id) {
        Rival rival = rivalRepository.findById(id)
                .orElseThrow(() -> new RivalException("Non se atopou o rival con id: " + id));

        return RivalUtil.mapRivalToResponseRivalDetails(rival);
    }

    @Override
    public Page<RivalItemResponse> getRivalsTable(Pageable pageable) {
        Page<Rival> rivalsPage = rivalRepository.findAllOfActualSeason(pageable);

        return rivalsPage.map(RivalUtil::mapRivalToResponseRivalItem);
    }

    @Override
    public List<GameTeamResponse> getRivalsByCategory(Long categoryId) {
        List<Rival> rivals = rivalRepository.findAllByCategoryIdAndActualSeason(categoryId);

        return rivals.stream().map(RivalUtil::mapRivalToGameTeamResponse).toList();
    }

    @Override
    @Transactional
    public RivalItemResponse saveRivalItem(RivalSaveRequest rivalSaveRequest) {
        Rival saved;
        if (rivalSaveRequest.getId() != null) {
            saved = this.updateRival(rivalSaveRequest);
        } else {
            saved = this.createRival(rivalSaveRequest);
        }

        return RivalUtil.mapRivalToResponseRivalItem(saved);
    }


    private Rival updateRival(RivalSaveRequest rivalSaveRequest) {
        Rival rival = rivalRepository.findById(rivalSaveRequest.getId())
                .orElseThrow(() -> new RivalException("Non se atopou o rival con id: " + rivalSaveRequest.getId()));

        return this.saveRivalEntity(rivalSaveRequest, rival);
    }

    private Rival createRival(RivalSaveRequest rivalSaveRequest) {
        Rival rival = new Rival();

        rival.setSeason(this.getActiveSeason());

        return saveRivalEntity(rivalSaveRequest, rival);
    }

    private Rival saveRivalEntity(RivalSaveRequest rivalSaveRequest, Rival rival) {
        rival.setName(rivalSaveRequest.getName());
        rival.setEmail(rivalSaveRequest.getEmail());
        rival.setTlf(rivalSaveRequest.getTlf());
        rival.setResponsible(rivalSaveRequest.getResponsible());

        if (rivalSaveRequest.getCategories() != null) {
            rival.setCategories(this.getCategories(rivalSaveRequest.getCategories()));
        } else {
            throw new IllegalArgumentException("Non pode non ter categorias asociadas");
        }


        if (rivalSaveRequest.getLogo() != null) {
            rival.setLogo(this.getImage(rivalSaveRequest.getLogo().getId()));
        }


        return rivalRepository.save(rival);
    }

    private Season getActiveSeason() {
        return seasonRepository.findByIsActiveTrue()
                .orElseThrow(() -> new SeasonException("Non se atopou Season activa"));
    }

    private List<Category> getCategories(List<Long> ids) {
        return categoryRepository.findAllByIdInAndIsActiveTrueOfActualSeason(ids);

    }

    private Image getImage(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ImageException("Non se atopou imaxe co id" + id));
    }


}
