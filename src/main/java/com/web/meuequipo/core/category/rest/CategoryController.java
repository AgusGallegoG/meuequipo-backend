package com.web.meuequipo.core.category.rest;

import com.web.meuequipo.core.category.dto.request.CategorySaveRequest;
import com.web.meuequipo.core.category.dto.response.CategoryResponse;
import com.web.meuequipo.core.category.service.CategoryService;
import com.web.meuequipo.core.shared.dto.SelectDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/dropdown")
    List<SelectDTO> getDropdownCategories() {
        return this.categoryService.getCategoriesDropdown();
    }


    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    List<CategoryResponse> getCategories() {
        return this.categoryService.getCategories();
    }

    @PostMapping("/")
    @PreAuthorize("isAuthenticated()")
    List<CategoryResponse> saveCategories(@RequestBody CategorySaveRequest categorySaveRequest) {
        this.categoryService.saveCategory(categorySaveRequest);
        return this.categoryService.getCategories();
    }
}
