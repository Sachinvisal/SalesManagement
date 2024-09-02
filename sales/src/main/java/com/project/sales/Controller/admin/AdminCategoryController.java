package com.project.sales.Controller.admin;

import com.project.sales.Dto.CategoryDto;
import com.project.sales.Entity.Category;
import com.project.sales.Services.admin.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor

public class AdminCategoryController {
    private final CategoryService categoryService;

    @PostMapping("category")
    public ResponseEntity<Category>createCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.createcategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);

    }

    @GetMapping("")
    public ResponseEntity<List<Category>>getAllCategorise(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

}
