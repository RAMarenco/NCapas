package org.example.controllerssec02.services;

import org.example.controllerssec02.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();

    Category findCategoryById(String id);
}
