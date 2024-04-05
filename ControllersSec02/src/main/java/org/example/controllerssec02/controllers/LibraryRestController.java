package org.example.controllerssec02.controllers;

import jakarta.validation.Valid;
import org.example.controllerssec02.domain.dtos.SaveBookDTO;
import org.example.controllerssec02.domain.entities.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryRestController {

    @GetMapping("/all")
    private ResponseEntity<List<Book>> findAll() {
        return new ResponseEntity<>(
                LibraryController.books,
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
