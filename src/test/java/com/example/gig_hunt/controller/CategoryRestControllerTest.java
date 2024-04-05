package com.example.gig_hunt.controller;

import com.example.gig_hunt.model.entity.Category;
import com.example.gig_hunt.model.entity.Master;
import com.example.gig_hunt.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest
public class CategoryRestControllerTest {

    private CategoryServiceImpl categoryService;
    private CategoryRestController categoryController;

    @BeforeEach
    public void setUp() {
        categoryService = mock(CategoryServiceImpl.class);
        categoryController = new CategoryRestController(categoryService);
    }

    @Test
    public void getAllCategories() {

        System.out.println("Test started");
        Set<Master> mastersOfCleaning = new HashSet<>();
        Category cleaning = new Category(100l, "cleaning", "description",
                false, mastersOfCleaning);

        Set<Master> mastersOfCrafting = new HashSet<>();
        Category crafting = new Category(101l, "crafting", "some description",
                false, mastersOfCrafting);

        List<Category> actual = new ArrayList<>();
        actual.add(cleaning);
        actual.add(crafting);

        // Mock behavior
        when(categoryService.getAll()).thenReturn(actual); // mock whatever behavior you need

        // Test the endpoint
        ResponseEntity<List<Category>> response = (ResponseEntity<List<Category>>) categoryController.getAllCategories();

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Add more assertions based on your specific implementation
    }

}