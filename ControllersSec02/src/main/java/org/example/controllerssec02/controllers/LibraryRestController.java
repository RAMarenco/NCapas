package org.example.controllerssec02.controllers;

import jakarta.validation.Valid;
import org.example.controllerssec02.domain.dtos.SaveBookDTO;
import org.example.controllerssec02.domain.entities.Book;
import org.example.controllerssec02.domain.dtos.GeneralResponse;
import org.example.controllerssec02.domain.dtos.PaginatedResponse;
import org.example.controllerssec02.domain.entities.Pagination;
import org.example.controllerssec02.services.BookService;
import org.example.controllerssec02.utils.ErrorMapper;
import org.example.controllerssec02.utils.PaginationTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryRestController {
    int pageSize = 5;

    private final PaginationTools<Book> paginationTools;
    private final BookService bookService;

    public LibraryRestController(PaginationTools<Book> paginationTools, BookService bookService, ErrorMapper errorMapper) {
        this.paginationTools = paginationTools;
        this.bookService = bookService;
    }

    @GetMapping("/all")
    private ResponseEntity<GeneralResponse> findAll() {
        return GeneralResponse.getResponse(
                HttpStatus.OK,
                "Book List Found!",
                bookService.findAll());
    }

    @GetMapping("/pagination")
    private ResponseEntity<Pagination> pagination() {
        int totalPages = paginationTools.getTotalPages(bookService.findAll(), pageSize);
        Pagination pagination = new Pagination(totalPages, pageSize);

        return new ResponseEntity<>(
                pagination,
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    private ResponseEntity<PaginatedResponse> findPage(@RequestParam int pageNumber) {
        if (pageNumber <= 0 || pageSize <= 0)
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );

        List<Book> paginatedData = paginationTools.pagination(bookService.findAll(), pageNumber, pageSize);

        return PaginatedResponse.getResponse(
                HttpStatus.OK,
                "List found",
                paginatedData,
                pageNumber
        );
    }

    @PostMapping("/save")
    private ResponseEntity<?> save(@RequestBody @Valid SaveBookDTO info) {
        bookService.save(info);

        return GeneralResponse.getResponse("Book Saved!");
    }

    @DeleteMapping("/{isbn}")
    private ResponseEntity<?> deleteByISBN(@PathVariable String isbn) {
        Book book = bookService.findByISBN(isbn);
        if (book == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Book not found!");
        }

        bookService.deleteByISBN(isbn);

        return GeneralResponse.getResponse("Book Deleted!");
    }
}
