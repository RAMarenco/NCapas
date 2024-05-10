package org.example.controllerssec02.services.impls;

import jakarta.transaction.Transactional;
import org.example.controllerssec02.domain.dtos.SaveBookDTO;
import org.example.controllerssec02.domain.entities.Book;
import org.example.controllerssec02.domain.entities.Category;
import org.example.controllerssec02.repositories.BookRepository;
import org.example.controllerssec02.services.BookService;
import org.example.controllerssec02.utils.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final StringTools tools;

    public BookServiceImpl(StringTools tools, BookRepository bookRepository) {
        this.tools = tools;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void save(SaveBookDTO info, Category category) {
        Book book = findByISBN(info.getISBN());

        if (book == null) {
            book = new Book();
        }

        book.setISBN(info.getISBN());
        book.setTitle(info.getTitle());
        book.setCategory(category);

        bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByISBN(String isbn) {
        return bookRepository.findByISBN(isbn).orElse(null);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteByISBN(String isbn) {
        bookRepository.deleteByISBN(isbn);
    }
}
