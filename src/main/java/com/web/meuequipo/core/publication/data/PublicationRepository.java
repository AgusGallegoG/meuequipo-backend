package com.web.meuequipo.core.publication.data;

import com.web.meuequipo.core.publication.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

    Page<Publication> findAll(Pageable pageable);
}
