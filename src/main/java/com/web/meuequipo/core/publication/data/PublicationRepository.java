package com.web.meuequipo.core.publication.data;

import com.web.meuequipo.core.publication.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

    @Query("""
            SELECT p
            FROM Publication p
            ORDER BY p.createdDate DESC
            """)
    Page<Publication> findAllOrderByCreatedDateDesc(Pageable pageable);

    @Query("""
            SELECT p
            FROM Publication p
            """)
    Page<Publication> findAllPage(Pageable pageable);

    @Query("""
            SELECT p
            FROM Publication p
            ORDER BY p.createdDate DESC
            """)
    List<Publication> findNewPublications(Pageable pageable);
}
