package com.web.meuequipo.core.signin.service;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.category.data.CategoryRepository;
import com.web.meuequipo.core.category.exception.CategoryException;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.data.SeasonRepository;
import com.web.meuequipo.core.season.exception.SeasonException;
import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import com.web.meuequipo.core.shared.enums.SigninState;
import com.web.meuequipo.core.signin.Player;
import com.web.meuequipo.core.signin.data.PlayerRepository;
import com.web.meuequipo.core.signin.dto.request.SigninRequest;
import com.web.meuequipo.core.signin.dto.response.SigninItemResponse;
import com.web.meuequipo.core.signin.dto.response.SigninResponse;
import com.web.meuequipo.core.signin.exception.PlayerException;
import com.web.meuequipo.core.signin.util.PlayerUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final CategoryRepository categoryRepository;

    private final SeasonRepository seasonRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, CategoryRepository categoryRepository, SeasonRepository seasonRepository) {
        this.playerRepository = playerRepository;
        this.categoryRepository = categoryRepository;
        this.seasonRepository = seasonRepository;
    }

    @Override
    public Page<SigninItemResponse> getSigninItemPage(Pageable pageable, Long categoryId, Long signinState) {
        Page<Player> players = playerRepository.findAllByOrCategoryOrBySigninStateOfActualSeason(categoryId, signinState, pageable);

        return players.map(PlayerUtil::mapPlayerToSigninItemResponse);
    }

    @Override
    public SigninResponse getSigninById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerException("Non se atopou a inscripcion co id: " + id));

        return PlayerUtil.mapPlayerToSigninResponse(player);
    }

    @Override
    public List<SelectDTO> getFreePlayersByCategory(Long categoryId) {
        List<Player> players = playerRepository.findAllFreeByCategory(categoryId);

        return players.stream().map(PlayerUtil::mapPlayerToSelectDTO).toList();
    }

    @Override
    @Transactional
    public void savePublicSignin(SigninRequest signinRequest) {
        Player player = initNewSigninEntity(signinRequest);

        player.setSigninState(SigninState.INIT.getId());//al crear se setea iniciada

        PlayerUtil.mapPublicSigninSingleFieldsToPlayer(signinRequest, player);

        playerRepository.save(player);

        //TODO :> Generate PDF + SendMail
    }


    @Override
    @Transactional
    public SigninItemResponse saveAdminSignin(SigninRequest signinRequest) {
        Player saved;
        if (signinRequest.getId() != null) {
            saved = updateSigninAdmin(signinRequest);
        } else {
            saved = createSigninAdmin(signinRequest);
        }

        return PlayerUtil.mapPlayerToSigninItemResponse(saved);
    }

    private Player initNewSigninEntity(SigninRequest signinRequest) {
        Player player = new Player();

        player.setSeason(this.getActiveSeason());
        player.setCategory(this.getCategory(signinRequest.getPlayer().getCategory()));

        return player;
    }

    private Player createSigninAdmin(SigninRequest signinRequest) {
        Player player = initNewSigninEntity(signinRequest);

        if (signinRequest.getState() != null) {
            SigninState state = SigninState.fromId(signinRequest.getId());
            player.setSigninState(state.getId());
        }

        PlayerUtil.mapPublicSigninSingleFieldsToPlayer(signinRequest, player);

        return playerRepository.save(player);

    }

    private Player updateSigninAdmin(SigninRequest signinRequest) {
        Player player = playerRepository.findById(signinRequest.getId())
                .orElseThrow(() -> new PlayerException("Non se atopou o xogador a editar"));

        if (signinRequest.getState() != null) {
            SigninState state = SigninState.fromId(signinRequest.getState());
            player.setSigninState(state.getId());
        }

        return playerRepository.save(player);
    }


    private Category getCategory(Long id) {
        return categoryRepository.findCategoryByIdAndIsActiveTrueOfActualSeason(id)
                .orElseThrow(() -> new CategoryException("Non se atopu o Category co id: " + id));
    }

    private Season getActiveSeason() {
        return seasonRepository.findByIsActiveTrue()
                .orElseThrow(() -> new SeasonException("Non se atopou Season activa"));
    }

}
