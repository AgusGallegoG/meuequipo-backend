package com.web.meuequipo.core.rival.service;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.category.data.CategoryRepository;
import com.web.meuequipo.core.image.Image;
import com.web.meuequipo.core.image.data.ImageRepository;
import com.web.meuequipo.core.image.exception.ImageException;
import com.web.meuequipo.core.rival.Rival;
import com.web.meuequipo.core.rival.data.RivalRepository;
import com.web.meuequipo.core.rival.dto.request.RequestSaveRival;
import com.web.meuequipo.core.rival.dto.response.ResponseRivalDetails;
import com.web.meuequipo.core.rival.dto.response.ResponseRivalItem;
import com.web.meuequipo.core.rival.exception.RivalException;
import com.web.meuequipo.core.rival.util.RivalUtil;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.data.SeasonRepository;
import com.web.meuequipo.core.shared.dto.response.ResponseMatchTeam;
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
    public ResponseRivalDetails getRivalDetails(Long id) {
        Rival rival = rivalRepository.findById(id)
                .orElseThrow(() -> new RivalException("Non se atopou o rival con id: " + id));

        return RivalUtil.mapRivalToResponseRivalDetails(rival);
    }

    @Override
    public Page<ResponseRivalItem> getRivalsTable(Pageable pageable) {
        Page<Rival> rivalsPage = rivalRepository.findAllOfActualCategory(pageable);

        return rivalsPage.map(RivalUtil::mapRivalToResponseRivalItem);
    }

    @Override
    public List<ResponseMatchTeam> getRivalsByCategory(Long categoryId) {
        List<Rival> rivals = rivalRepository.findAllByCategoryIdAndActualSeason(categoryId);

        return rivals.stream().map(RivalUtil::mapRivalToResponseMatchTeam).toList();
    }

    @Override
    public ResponseRivalItem saveRivalItem(RequestSaveRival requestSaveRival) {
        Rival saved;
        if (requestSaveRival.getId() != null) {
            saved = this.updateRival(requestSaveRival);
        } else {
            saved = this.createRival(requestSaveRival);
        }

        return RivalUtil.mapRivalToResponseRivalItem(saved);
    }


    private Rival updateRival(RequestSaveRival requestSaveRival) {
        Rival rival = rivalRepository.findById(requestSaveRival.getId())
                .orElseThrow(() -> new RivalException("Non se atopou o rival con id: " + requestSaveRival.getId()));

        return this.saveRivalEntity(requestSaveRival, rival);
    }

    private Rival createRival(RequestSaveRival requestSaveRival) {
        Season season = this.seasonRepository.findByIsActiveTrue()
                .orElseThrow(() -> new IllegalStateException("Non se atopou unha temporada activa"));
        Rival rival = new Rival();

        rival.setSeason(season);

        return saveRivalEntity(requestSaveRival, rival);
    }

    private Rival saveRivalEntity(RequestSaveRival requestSaveRival, Rival rival) {
        rival.setName(requestSaveRival.getName());
        rival.setEmail(requestSaveRival.getEmail());
        rival.setTlf(requestSaveRival.getTlf());
        rival.setResponsible(requestSaveRival.getResponsible());

        if (requestSaveRival.getCategories() != null) {
            List<Category> categories = categoryRepository.findAllById(requestSaveRival.getCategories());

            rival.setCategories(categories);
        } else {
            throw new IllegalArgumentException("Non pode non ter categorias asociadas");
        }


        if (requestSaveRival.getLogo() != null) {
            Image image = imageRepository.findById(requestSaveRival.getLogo().getId())
                    .orElseThrow(() -> new ImageException("Non se atopou imaxe co id" + requestSaveRival.getLogo().getId()));
            rival.setLogo(image);
        }


        return rivalRepository.save(rival);
    }
}
