package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.Category;
import com.example.gig_hunt.model.repository.CategoryRepository;
import com.example.gig_hunt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category readById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category createOrUpdate(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
