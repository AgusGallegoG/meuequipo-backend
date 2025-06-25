package com.web.meuequipo.core.squad.service;


import com.web.meuequipo.core.game.Game;
import com.web.meuequipo.core.game.data.GameRepository;
import com.web.meuequipo.core.game.exception.GameException;
import com.web.meuequipo.core.signin.Player;
import com.web.meuequipo.core.signin.data.PlayerRepository;
import com.web.meuequipo.core.squad.Squad;
import com.web.meuequipo.core.squad.data.SquadRepository;
import com.web.meuequipo.core.squad.dto.request.SquadCreateRequest;
import com.web.meuequipo.core.squad.dto.response.SquadResponse;
import com.web.meuequipo.core.squad.exception.SquadException;
import com.web.meuequipo.core.squad.util.SquadUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SquadServiceImpl implements SquadService {

    private final SquadRepository squadRepository;

    private final PlayerRepository playerRepository;

    private final GameRepository gameRepository;


    public SquadServiceImpl(SquadRepository squadRepository, PlayerRepository playerRepository, GameRepository gameRepository) {
        this.squadRepository = squadRepository;
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    @Transactional
    public SquadResponse create(SquadCreateRequest request) {
        Squad squad = new Squad();

        squad.setGame(getGame(request.getGame()));
        squad.setPlayers(getSquadPlayers(request.getPlayers()));
        squad.setMeetingPoint(request.getLocationMeeting());
        squad.setMeetingTime(request.getDateMeeting());

        //TODO => Mails

        return SquadUtil.createSquadResponse(squadRepository.save(squad));
    }

    private Game getGame(Long id) {
        return gameRepository.findGameByIdOfActualSeason(id)
                .orElseThrow(() -> new GameException("Non s epuido atopar o Game co id:" + id));
    }

    private List<Player> getSquadPlayers(List<Long> ids) {
        List<Player> playersToSquad = playerRepository.findAllByIdOfActualSeason(ids);
        if (playersToSquad.isEmpty() || playersToSquad.size() != ids.size()) {
            throw new SquadException("Non se puideron engadir todos os xogadores a convocatoria");
        }
        return playersToSquad;
    }

}
