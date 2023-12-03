package com.example.gig_hunt.model.repository;

import com.example.gig_hunt.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM Category c WHERE c.available_online = true", nativeQuery = true)
    List<Category> getOnlineCategories();

    @Query(value = "SELECT * FROM Category c WHERE c.available_online = false", nativeQuery = true)
    List<Category> getOfflineCategories();

}
