package com.web.meuequipo.core.category.util;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.category.dto.response.CategoryResponse;
import com.web.meuequipo.core.shared.dto.response.SelectDTO;

public class CategoryUtil {

    public static SelectDTO toSelect(Category category) {
        return new SelectDTO(category.getId(), category.getName());
    }

    public static CategoryResponse createCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setYearInit(category.getYearInit());
        categoryResponse.setYearEnd(category.getYearEnd());
        categoryResponse.setActive(category.isActive());

        return categoryResponse;
    }

}
