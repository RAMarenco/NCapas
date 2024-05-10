package org.example.controllerssec02.repositories;

import org.example.controllerssec02.domain.entities.Book;
import org.example.controllerssec02.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findByISBN(String isbn);
    List<Book> findAllByCategoryOrTitleStartingWith(Category category, String prefix);
    void deleteByISBN(String isbn);
    boolean existsByISBN(String isbn);
}
