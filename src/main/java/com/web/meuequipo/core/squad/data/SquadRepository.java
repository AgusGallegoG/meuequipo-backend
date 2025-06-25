package com.web.meuequipo.core.squad.data;

import com.web.meuequipo.core.squad.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Long> {
}
