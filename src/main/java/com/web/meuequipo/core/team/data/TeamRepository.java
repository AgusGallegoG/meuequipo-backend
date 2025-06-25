package com.web.meuequipo.core.team.data;

import com.web.meuequipo.core.team.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("""
            SELECT t
            FROM Team t
            WHERE t.season.isActive=true
            """)
    Page<Team> findTeamsOfActualSeason(Pageable pageable);

    @Query("""
            SELECT t
            FROM Team t
            WHERE t.season.isActive=true
            """)
    List<Team> findTeamsOfActualSeason();

    @Query("""
                SELECT t
                FROM Team t
                JOIN Category c
                WHERE c.id = :categoryId
                AND c.isActive = true
                AND t.season.isActive=true
            """)
    List<Team> findTeamsByCategoryIdOfActualSeason(Long categoryId);

    @Query("""
            SELECT t
            FROM Team t
            WHERE t.season.isActive=true
            AND t.id = :id
            """)
    Optional<Team> findTeamByIdOfActualSeason(@Param("id") Long id);
}
