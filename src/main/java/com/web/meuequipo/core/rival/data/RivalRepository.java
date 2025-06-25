package com.web.meuequipo.core.rival.data;

import com.web.meuequipo.core.rival.Rival;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RivalRepository extends JpaRepository<Rival, Long> {

    @Query("""
                SELECT r
                FROM Rival r
                JOIN r.categories c
                WHERE c.id = :categoryId
                AND c.isActive=true
                AND r.season.isActive = true
            """)
    List<Rival> findAllByCategoryIdAndActualSeason(Long categoryId);

    @Query("""
                SELECT r
                FROM Rival r
                WHERE r.season.isActive = true
            """)
    Page<Rival> findAllOfActualSeason(Pageable pageable);

    @Query("""
                SELECT r
                FROM Rival r
                WHERE r.season.isActive = true
                AND r.id = :id
            """)
    Optional<Rival> findRivalByIdOfActualSeason(@Param("id") Long id);
}
