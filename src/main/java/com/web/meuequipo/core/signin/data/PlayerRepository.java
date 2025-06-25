package com.web.meuequipo.core.signin.data;

import com.web.meuequipo.core.signin.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @Query("""
            SELECT p
            FROM Player p
            WHERE p.category.id = :categoryId
            AND p.team IS NULL
            AND p.season.isActive=true
            """)
    List<Player> findAllFreeByCategory(@Param("categoryId") Long categoryId);


    @Query("""
            SELECT p
            FROM Player p
            WHERE p.season.isActive=true
            AND (:categoryId IS NULL OR p.category.id = :categoryId)
            AND (:signinState IS NULL OR p.signinState = :signinState)
            """)
    Page<Player> findAllByOrCategoryOrBySigninStateOfActualSeason(@Param("categoryId") Long categoryId, @Param("signinState") Long signinState, Pageable pageable);
}
