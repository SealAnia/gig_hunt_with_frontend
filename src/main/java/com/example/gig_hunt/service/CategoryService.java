package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.Category;

import java.util.List;

public interface CategoryService extends DefaultService<Category> {

    List<Category> getOnlineCategories();
    List<Category> getOfflineCategories();

}
