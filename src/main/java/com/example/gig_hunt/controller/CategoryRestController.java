package com.example.gig_hunt.controller;

import com.example.gig_hunt.model.entity.Category;
import com.example.gig_hunt.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/categories")
public class CategoryRestController {

    private final CategoryServiceImpl categoryService;

    @Autowired
    public CategoryRestController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping(value = "/{categoryId}")
    public Category getCategoryById(@PathVariable Long categoryId) {
        return categoryService.readById(categoryId);
    }

    @PostMapping(value = "/")
    public void createCategory(@RequestBody Category category) {
        categoryService.createOrUpdate(category);
    }

    @PutMapping(value = "/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        Category updatedCategory = categoryService.readById(categoryId);
        updatedCategory.setName(category.getName());
        updatedCategory.setComment(category.getComment());
        updatedCategory.setAvailableOnline(category.isAvailableOnline());
        categoryService.createOrUpdate(updatedCategory);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping(value = "/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
    }

}
