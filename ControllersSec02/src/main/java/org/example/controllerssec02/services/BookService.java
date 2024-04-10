package org.example.controllerssec02.services;

import org.example.controllerssec02.domain.dtos.SaveBookDTO;
import org.example.controllerssec02.domain.entities.Book;

import java.util.List;

public interface BookService {
    void save(SaveBookDTO info);
    List<Book> findAll();
    Book findByISBN(String isbn);
    void deleteByISBN(String isbn);
}
