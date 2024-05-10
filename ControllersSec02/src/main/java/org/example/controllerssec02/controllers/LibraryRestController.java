package org.example.controllerssec02.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.controllerssec02.domain.dtos.CategoryDTO;
import org.example.controllerssec02.domain.dtos.SaveBookDTO;
import org.example.controllerssec02.domain.entities.Book;
import org.example.controllerssec02.domain.dtos.GeneralResponse;
import org.example.controllerssec02.domain.dtos.PaginatedResponse;
import org.example.controllerssec02.domain.entities.Category;
import org.example.controllerssec02.domain.entities.Pagination;
import org.example.controllerssec02.services.BookService;
import org.example.controllerssec02.services.CategoryService;
import org.example.controllerssec02.utils.ErrorMapper;
import org.example.controllerssec02.utils.PaginationTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@Slf4j
public class LibraryRestController {
    int pageSize = 5;

    private final PaginationTools<Book> paginationTools;
    private final BookService bookService;
    public final CategoryService categoryService;

    public LibraryRestController(PaginationTools<Book> paginationTools, BookService bookService, ErrorMapper errorMapper, CategoryService categoryService) {
        this.paginationTools = paginationTools;
        this.bookService = bookService;
        this.categoryService = categoryService;
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
        Category category = categoryService.findCategoryById(info.getCategory());

        if (category == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND);
        }

        bookService.save(info, category);

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

    @GetMapping("/categories")
    private ResponseEntity<GeneralResponse> findAllCategories() {
        return GeneralResponse.getResponse(categoryService.findAllCategories());
    }

    @GetMapping("/category/{id}")
    private ResponseEntity<GeneralResponse> findCategoryById(@PathVariable String id) {
        String search = id.toUpperCase();

        Category category = categoryService.findCategoryById(search);

        if (category == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Category not found!");
        }

        return GeneralResponse.getResponse(HttpStatus.OK,"Category Found", category);
    }

    @PostMapping("/category")
    private ResponseEntity<?> saveCategory(@RequestBody @Valid CategoryDTO info) {
        categoryService.save(info);

        return GeneralResponse.getResponse("Category Saved!");
    }
}
