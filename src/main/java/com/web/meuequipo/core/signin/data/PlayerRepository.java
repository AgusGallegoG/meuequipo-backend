package com.web.meuequipo.core.signin.data;

import com.web.meuequipo.core.signin.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("""
            SELECT p
            FROM Player p
            WHERE p.id in :playerIds
            AND p.season.isActive=true
            """)
    List<Player> findAllByIdOfActualSeason(@Param("playerIds") List<Long> playerIds);
}
