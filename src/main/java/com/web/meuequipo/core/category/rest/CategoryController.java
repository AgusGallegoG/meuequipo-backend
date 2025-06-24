package com.web.meuequipo.core.category.rest;

import com.web.meuequipo.core.category.dto.request.CategorySaveRequest;
import com.web.meuequipo.core.category.dto.response.CategoryResponse;
import com.web.meuequipo.core.category.service.CategoryService;
import com.web.meuequipo.core.shared.dto.response.SelectDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/public/dropdown")
    public List<SelectDTO> getDropdownCategories() {
        return this.categoryService.getCategoriesDropdown();
    }


    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public List<CategoryResponse> getCategories() {
        return this.categoryService.getCategories();
    }

    @PostMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CategoryResponse> saveCategories(@RequestBody CategorySaveRequest categorySaveRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.saveCategory(categorySaveRequest));
    }
}
