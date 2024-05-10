package org.example.controllerssec02.repositories;

import org.example.controllerssec02.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {}
