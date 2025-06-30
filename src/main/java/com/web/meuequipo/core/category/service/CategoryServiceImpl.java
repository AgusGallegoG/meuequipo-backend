package com.web.meuequipo.core.category.service;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.category.data.CategoryRepository;
import com.web.meuequipo.core.category.dto.request.CategorySaveRequest;
import com.web.meuequipo.core.category.dto.response.CategoryResponse;
import com.web.meuequipo.core.category.exception.CategoryException;
import com.web.meuequipo.core.category.util.CategoryUtil;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.data.SeasonRepository;
import com.web.meuequipo.core.season.exception.SeasonException;
import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        return allCategories.stream().map(CategoryUtil::toSelect).toList();
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<Category> allCategories = categoryRepository.findAllCategoryActiveActualSeason();
        if (allCategories == null) {
            return new ArrayList<>();
        }

        return allCategories.stream().map(CategoryUtil::createCategoryResponse).toList();
    }

    @Override
    @Transactional
    public CategoryResponse saveCategory(CategorySaveRequest categorySaveRequest) {
        Category saved;
        if (categorySaveRequest.getId() == null) {
            saved = this.createCategory(categorySaveRequest);
        } else {
            saved = this.updateCategory(categorySaveRequest);
        }

        return CategoryUtil.createCategoryResponse(saved);
    }

    private Category createCategory(CategorySaveRequest categorySaveRequest) {
        Category category = new Category();

        category.setSeason(this.getActiveSeason());

        return saveCategoryEntity(categorySaveRequest, category);
    }

    private Category updateCategory(CategorySaveRequest categorySaveRequest) {
        Category category = categoryRepository.findCategoryOfActualSeason(categorySaveRequest.getId())
                .orElseThrow(() -> new CategoryException("Non se atopa a categoría en BD"));

        return saveCategoryEntity(categorySaveRequest, category);
    }

    private Category saveCategoryEntity(CategorySaveRequest categorySaveRequest, Category category) {
        category.setName(categorySaveRequest.getName());
        category.setYearInit(categorySaveRequest.getYearInit());
        category.setYearEnd(categorySaveRequest.getYearEnd());
        category.setActive(categorySaveRequest.isActive());

        return categoryRepository.save(category);
    }

    private Season getActiveSeason() {
        return seasonRepository.findByIsActiveTrue().orElseThrow(() -> new SeasonException("Non se atopou Season activa"));
    }
}
