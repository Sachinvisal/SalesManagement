package com.project.sales.Services.admin.category;

import com.project.sales.Dto.CategoryDto;
import com.project.sales.Entity.Category;

import java.util.List;

public interface CategoryService {
    Category createcategory(CategoryDto categoryDto);

    List<Category> getAllCategories();
}
