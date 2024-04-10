package org.example.controllerssec02.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.controllerssec02.domain.dtos.SaveBookDTO;
import org.example.controllerssec02.domain.entities.Book;
import org.example.controllerssec02.domain.entities.Pagination;
import org.example.controllerssec02.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/library")
public class LibraryController {
    private final BookService bookService;

    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    private String getBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book-list";
    }

    @GetMapping("/")
    private String mainPage() {
        return "index";
    }

    @GetMapping("/rest")
    private String restPage(Model model) {
        model.addAttribute("books", bookService.findAll().subList(0,5));
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
        bookService.save(info);

        return "redirect:/library/all";
    }


}
