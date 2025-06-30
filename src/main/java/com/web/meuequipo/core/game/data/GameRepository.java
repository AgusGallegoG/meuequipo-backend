package com.web.meuequipo.core.game.data;

import com.web.meuequipo.core.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("""
                SELECT g
                FROM Game g
                WHERE g.season.isActive = true
                AND (:from IS NULL OR g.gameDate >= :from)
                AND (:to IS NULL OR g.gameDate <= :to)
                AND (:teamId IS NULL OR g.team.id = :teamId)
                AND (:private IS NULL or g.gameState <> :private)
                AND (:canceled IS NULL or  g.gameState <> :canceled)
            """)
    List<Game> findAllByFilters(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to, @Param("teamId") Long teamId, @Param("private") Long privateId, @Param("canceled") Long canceled);


    @Query("""
            SELECT g
            FROM Game g
            WHERE g.season.isActive
            AND g.id = :id
            """)
    Optional<Game> findGameByIdOfActualSeason(@Param("id") Long id);
}
