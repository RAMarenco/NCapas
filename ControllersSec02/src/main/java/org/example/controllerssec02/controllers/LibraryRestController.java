package org.example.controllerssec02.controllers;

import jakarta.validation.Valid;
import org.example.controllerssec02.domain.dtos.SaveBookDTO;
import org.example.controllerssec02.domain.entities.Book;
import org.example.controllerssec02.domain.entities.GeneralResponse;
import org.example.controllerssec02.domain.entities.PaginatedResponse;
import org.example.controllerssec02.domain.entities.Pagination;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryRestController {

    @GetMapping("/all")
    private ResponseEntity<GeneralResponse<Book>> findAll() {
        GeneralResponse<Book> responseBody = new GeneralResponse<>(LibraryController.books);
        return new ResponseEntity<>(
                responseBody,
                HttpStatus.OK
        );
    }

    @GetMapping("/pagination")
    private ResponseEntity<Pagination> pagination() {
        int pageSize = 5;
        int totalBooks = LibraryController.books.size();
        int totalPages = (int) Math.ceil((double) totalBooks / pageSize);
        Pagination pagination = new Pagination(totalPages, pageSize);

        return new ResponseEntity<>(
                pagination,
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    private ResponseEntity<PaginatedResponse<Book>> findPage(@RequestParam int pageNumber) {
        int pageSize = 5;
        if (pageNumber <= 0 || pageSize <= 0)
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );

        int totalBooks = LibraryController.books.size();
        int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

        if (pageNumber > totalPages) {
            throw new IllegalArgumentException("Page number exceeds total number of pages.");
        }

        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, LibraryController.books.size());

        List<Book> pageBooks = LibraryController.books.subList(startIndex, endIndex);

        PaginatedResponse<Book> responseBody = new PaginatedResponse<>(pageBooks, pageNumber);

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

        Book _book = LibraryController.books.stream()
                .filter(b -> b.getISBN().equals(info.getISBN()))
                .findAny()
                .orElse(null);

        if (_book == null) {
            Book newBook = new Book(info.getISBN(), info.getTitle());
            LibraryController.books.add(newBook);
        } else {
            _book.setTitle(info.getTitle());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
