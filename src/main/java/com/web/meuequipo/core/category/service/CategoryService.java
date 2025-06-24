package com.web.meuequipo.core.category.service;

import com.web.meuequipo.core.category.dto.request.CategorySaveRequest;
import com.web.meuequipo.core.category.dto.response.CategoryResponse;
import com.web.meuequipo.core.shared.dto.response.SelectDTO;

import java.util.List;

public interface CategoryService {
    List<SelectDTO> getCategoriesDropdown();

    List<CategoryResponse> getCategories();

    CategoryResponse saveCategory(CategorySaveRequest categorySaveRequest);
}
