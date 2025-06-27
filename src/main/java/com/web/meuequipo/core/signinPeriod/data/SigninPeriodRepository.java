package com.web.meuequipo.core.signinPeriod.data;

import com.web.meuequipo.core.signinPeriod.SigninPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SigninPeriodRepository extends JpaRepository<SigninPeriod, Long> {

    @Query("""
                SELECT sp FROM SigninPeriod sp WHERE sp.season.isActive = true
            """)
    Optional<SigninPeriod> findPeriodOfActualSeason();
}
