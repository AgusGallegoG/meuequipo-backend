package com.web.meuequipo.core.rival.data;

import com.web.meuequipo.core.rival.Rival;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    Page<Rival> findAllOfActualCategory(Pageable pageable);
}
