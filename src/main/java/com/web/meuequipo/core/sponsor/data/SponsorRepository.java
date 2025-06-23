package com.web.meuequipo.core.sponsor.data;

import com.web.meuequipo.core.sponsor.Sponsor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    @Query("""
                SELECT s
                FROM Sponsor s
                WHERE s.season.isActive = true
            """)
    List<Sponsor> findAllOfActualSeason();

    @Query("""
                SELECT s
                FROM Sponsor s
                WHERE s.season.isActive = true
            """)
    Page<Sponsor> findAllOfActualSeason(Pageable pageable);
}
