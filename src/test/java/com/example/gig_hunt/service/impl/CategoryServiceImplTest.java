package com.example.gig_hunt.service.impl;

import com.example.gig_hunt.model.entity.Category;
import com.example.gig_hunt.model.entity.Master;
import com.example.gig_hunt.model.repository.CategoryRepository;
import jakarta.persistence.SecondaryTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
    public class CategoryServiceImplTest {

        @InjectMocks
        CategoryServiceImpl sategoryServiceImpl;

        @Mock
        CategoryRepository categoryRepo;

        //@BeforeEach
        //public void init() {
        //MockitoAnnotations.openMocks(this);
        //}

        @BeforeEach
        void setUp() throws Exception {
            System.out.println("Process started");
        }

        @AfterEach
        void tearDown() throws Exception {
        }

        @Test
        void test() {
            fail("Not yet implemented");
        }

        @Test
        public void getAll() {
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

            when(categoryRepo.findAll()).thenReturn(actual);

            List<Category> categoryList = sategoryServiceImpl.getAll();

            assertEquals(2, categoryList.size());
            verify(categoryRepo, times(1)).findAll();

        }

    }
