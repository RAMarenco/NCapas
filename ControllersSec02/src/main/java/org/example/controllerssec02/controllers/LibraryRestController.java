package org.example.controllerssec02.controllers;

import jakarta.validation.Valid;
import org.example.controllerssec02.domain.dtos.SaveBookDTO;
import org.example.controllerssec02.domain.entities.Book;
import org.example.controllerssec02.domain.entities.GeneralResponse;
import org.example.controllerssec02.domain.entities.PaginatedResponse;
import org.example.controllerssec02.domain.entities.Pagination;
import org.example.controllerssec02.services.BookService;
import org.example.controllerssec02.services.PaginationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryRestController {
    int pageSize = 5;

    private final PaginationService<Book> paginationService;
    private final BookService bookService;

    public LibraryRestController(PaginationService<Book> paginationService, BookService bookService) {
        this.paginationService = paginationService;
        this.bookService = bookService;
    }

    @GetMapping("/all")
    private ResponseEntity<GeneralResponse<Book>> findAll() {
        GeneralResponse<Book> responseBody = new GeneralResponse<>(bookService.findAll());
        return new ResponseEntity<>(
                responseBody,
                HttpStatus.OK
        );
    }

    @GetMapping("/pagination")
    private ResponseEntity<Pagination> pagination() {
        int totalPages = paginationService.getTotalPages(bookService.findAll(), pageSize);
        Pagination pagination = new Pagination(totalPages, pageSize);

        return new ResponseEntity<>(
                pagination,
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    private ResponseEntity<PaginatedResponse<Book>> findPage(@RequestParam int pageNumber) {
        if (pageNumber <= 0 || pageSize <= 0)
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );

        List<Book> paginatedData = paginationService.pagination(bookService.findAll(), pageNumber, pageSize);
        PaginatedResponse<Book> responseBody = new PaginatedResponse<>(paginatedData, pageNumber);

        return new ResponseEntity<>(
                responseBody,
                HttpStatus.OK
        );
    }

    @PostMapping("/save")
    private ResponseEntity<?> save(@RequestBody @Valid SaveBookDTO info, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(
                    "Bad Request",
                    HttpStatus.BAD_REQUEST
            );
        }

        bookService.save(info);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    private ResponseEntity<?> deleteByISBN(@PathVariable String isbn) {
        Book book = bookService.findByISBN(isbn);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookService.deleteByISBN(isbn);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
