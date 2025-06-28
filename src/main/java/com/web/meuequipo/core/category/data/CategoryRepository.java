package com.web.meuequipo.core.category.data;

import com.web.meuequipo.core.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("""
            SELECT c
            FROM Category c
            WHERE c.season.isActive = true
            """)
    List<Category> findAllCategoryActiveActualSeason();

    @Query("""
            SELECT c
            FROM Category c
            WHERE c.season.isActive = true
            AND c.isActive = true
            AND c.id = :id""")
    Optional<Category> findCategoryByIdAndIsActiveTrueOfActualSeason(@Param("id") Long id);

    @Query("""
            SELECT c
            FROM Category c
            WHERE c.season.isActive = true
            AND c.isActive = true
            AND c.id IN (:ids)""")
    List<Category> findAllByIdInAndIsActiveTrueOfActualSeason(@Param("ids") List<Long> ids);
}
