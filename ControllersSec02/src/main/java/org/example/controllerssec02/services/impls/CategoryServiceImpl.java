package org.example.controllerssec02.services.impls;

import lombok.extern.slf4j.Slf4j;
import org.example.controllerssec02.domain.entities.Category;
import org.example.controllerssec02.repositories.CategoryRepository;
import org.example.controllerssec02.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl  implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
