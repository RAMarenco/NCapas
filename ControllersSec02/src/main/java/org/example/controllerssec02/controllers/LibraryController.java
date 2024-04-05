package org.example.controllerssec02.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.controllerssec02.domain.dtos.SaveBookDTO;
import org.example.controllerssec02.domain.entities.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/library")
public class LibraryController {

    // IMAGINACION
    public static final List<Book> books = new ArrayList<>();
    static {
        books.add(new Book("xxxxxxxxx-a", "test0"));
        books.add(new Book("xxxxxxxxx-b", "test1"));
        books.add(new Book("xxxxxxxxx-c", "test2"));
        books.add(new Book("xxxxxxxxx-d", "test3"));
        books.add(new Book("xxxxxxxxx-e", "test4"));
        books.add(new Book("xxxxxxxxx-f", "test5"));
        books.add(new Book("xxxxxxxxx-g", "test6"));
    }

    @GetMapping("/all")
    private String getBooks(Model model) {
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/")
    private String mainPage() {
        return "index";
    }

    @GetMapping("/rest")
    private String restPage(Model model) {
        model.addAttribute("books", books);
        return "index-rest-h";
    }

    @PostMapping("/save")
    private String saveBook(@ModelAttribute @Valid SaveBookDTO info, BindingResult errors) {
        //log.info(info.toString());

        if (errors.hasErrors()) {
            log.info("Hay errores");
            log.info(errors.toString());
            log.info(info.toString());
            return "errorsito";
        }

        //IMAGINACION (Aqui van los servicios)
        Book _book = books.stream()
                .filter(b -> b.getISBN().equals(info.getISBN()))
                .findAny()
                .orElse(null);

        if (_book == null) {
            Book newBook = new Book(info.getISBN(), info.getTitle());
            books.add(newBook);
        } else {
            _book.setTitle(info.getTitle());
        }

        return "redirect:/library/all";
    }


}
