package com.web.meuequipo.core.category.service;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.category.data.CategoryRepository;
import com.web.meuequipo.core.category.dto.request.CategorySaveRequest;
import com.web.meuequipo.core.category.dto.response.CategoryResponse;
import com.web.meuequipo.core.category.exception.CategoryException;
import com.web.meuequipo.core.category.util.CategoryUtil;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.data.SeasonRepository;
import com.web.meuequipo.core.shared.dto.SelectDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SeasonRepository seasonRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, SeasonRepository seasonRepository) {
        this.categoryRepository = categoryRepository;
        this.seasonRepository = seasonRepository;
    }

    @Override
    public List<SelectDTO> getCategoriesDropdown() {
        List<Category> allCategories = categoryRepository.findAllCategoryActiveActualSeason();
        return allCategories.stream().map(CategoryUtil::toSelect).collect(Collectors.toList());
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<Category> allCategories = categoryRepository.findAllCategoryActiveActualSeason();
        if (allCategories == null) {
            return new ArrayList<>();
        }

        return allCategories.stream().map(CategoryUtil::createCategoryResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveCategory(CategorySaveRequest categorySaveRequest) {
        if (categorySaveRequest.getId() == null) {
            this.createCategory(categorySaveRequest);
        } else {
            this.updateCategory(categorySaveRequest);
        }
    }

    private void createCategory(CategorySaveRequest categorySaveRequest) {
        Season season = this.seasonRepository.findByIsActiveTrue()
                .orElseThrow(() -> new IllegalStateException("Non se atopou unha temporada activa"));
        Category category = new Category();

        category.setName(categorySaveRequest.getName());
        category.setYearInit(categorySaveRequest.getYearInit());
        category.setYearEnd(categorySaveRequest.getYearEnd());
        category.setSeason(season);
        category.setActive(categorySaveRequest.isActive());

        categoryRepository.save(category);
    }

    private void updateCategory(CategorySaveRequest categorySaveRequest) {
        Category category = categoryRepository.findById(categorySaveRequest.getId())
                .orElseThrow(() -> new CategoryException("Non se atopa a categoría en BD"));

        category.setName(categorySaveRequest.getName());
        category.setYearInit(categorySaveRequest.getYearInit());
        category.setYearEnd(categorySaveRequest.getYearEnd());
        category.setActive(categorySaveRequest.isActive());

        categoryRepository.save(category);
    }
}
