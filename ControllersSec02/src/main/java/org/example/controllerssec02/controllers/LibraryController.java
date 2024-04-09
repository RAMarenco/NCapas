package org.example.controllerssec02.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.controllerssec02.domain.dtos.SaveBookDTO;
import org.example.controllerssec02.domain.entities.Book;
import org.example.controllerssec02.domain.entities.Pagination;
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
        books.add(new Book("xxxxxxxxx-h", "test7"));
        books.add(new Book("xxxxxxxxx-i", "test8"));
        books.add(new Book("xxxxxxxxx-j", "test9"));
        books.add(new Book("xxxxxxxxx-k", "test10"));
        books.add(new Book("xxxxxxxxx-l", "test11"));
        books.add(new Book("xxxxxxxxx-m", "test12"));
        books.add(new Book("xxxxxxxxx-n", "test13"));
        books.add(new Book("xxxxxxxxx-o", "test14"));
        books.add(new Book("xxxxxxxxx-p", "test15"));
        books.add(new Book("xxxxxxxxx-q", "test16"));
        books.add(new Book("xxxxxxxxx-r", "test17"));
        books.add(new Book("xxxxxxxxx-s", "test18"));
        books.add(new Book("xxxxxxxxx-t", "test19"));
        books.add(new Book("xxxxxxxxx-u", "test20"));
        books.add(new Book("xxxxxxxxx-v", "test21"));
        books.add(new Book("xxxxxxxxx-w", "test22"));
        books.add(new Book("xxxxxxxxx-x", "test23"));
        books.add(new Book("xxxxxxxxx-y", "test24"));
        books.add(new Book("xxxxxxxxx-z", "test25"));
        books.add(new Book("xxxxxxxxx-aa", "test26"));
        books.add(new Book("xxxxxxxxx-ab", "test27"));
        books.add(new Book("xxxxxxxxx-ac", "test28"));
        books.add(new Book("xxxxxxxxx-ad", "test29"));
        books.add(new Book("xxxxxxxxx-ae", "test30"));
        books.add(new Book("xxxxxxxxx-af", "test31"));
        books.add(new Book("xxxxxxxxx-ag", "test32"));
        books.add(new Book("xxxxxxxxx-ah", "test33"));
        books.add(new Book("xxxxxxxxx-ai", "test34"));
        books.add(new Book("xxxxxxxxx-aj", "test35"));
        books.add(new Book("xxxxxxxxx-ak", "test36"));
        books.add(new Book("xxxxxxxxx-al", "test37"));
        books.add(new Book("xxxxxxxxx-am", "test38"));
        books.add(new Book("xxxxxxxxx-an", "test39"));
        books.add(new Book("xxxxxxxxx-ao", "test40"));
        books.add(new Book("xxxxxxxxx-ap", "test41"));
        books.add(new Book("xxxxxxxxx-aq", "test42"));
        books.add(new Book("xxxxxxxxx-ar", "test43"));
        books.add(new Book("xxxxxxxxx-as", "test44"));
        books.add(new Book("xxxxxxxxx-at", "test45"));
        books.add(new Book("xxxxxxxxx-au", "test46"));
        books.add(new Book("xxxxxxxxx-av", "test47"));
        books.add(new Book("xxxxxxxxx-aw", "test48"));
        books.add(new Book("xxxxxxxxx-ax", "test49"));
        books.add(new Book("xxxxxxxxx-ay", "test50"));
        books.add(new Book("xxxxxxxxx-az", "test51"));
        books.add(new Book("xxxxxxxxx-ba", "test52"));
        books.add(new Book("xxxxxxxxx-bb", "test53"));
        books.add(new Book("xxxxxxxxx-bc", "test54"));
        books.add(new Book("xxxxxxxxx-bd", "test55"));
        books.add(new Book("xxxxxxxxx-be", "test56"));
        books.add(new Book("xxxxxxxxx-bf", "test57"));
        books.add(new Book("xxxxxxxxx-bg", "test58"));
        books.add(new Book("xxxxxxxxx-bh", "test59"));
        books.add(new Book("xxxxxxxxx-bi", "test60"));
        books.add(new Book("xxxxxxxxx-bj", "test61"));
        books.add(new Book("xxxxxxxxx-bk", "test62"));
        books.add(new Book("xxxxxxxxx-bl", "test63"));
        books.add(new Book("xxxxxxxxx-bm", "test64"));
        books.add(new Book("xxxxxxxxx-bn", "test65"));
        books.add(new Book("xxxxxxxxx-bo", "test66"));
        books.add(new Book("xxxxxxxxx-bp", "test67"));
        books.add(new Book("xxxxxxxxx-bq", "test68"));
        books.add(new Book("xxxxxxxxx-br", "test69"));
        books.add(new Book("xxxxxxxxx-bs", "test70"));
        books.add(new Book("xxxxxxxxx-bt", "test71"));
        books.add(new Book("xxxxxxxxx-bu", "test72"));
        books.add(new Book("xxxxxxxxx-bv", "test73"));
        books.add(new Book("xxxxxxxxx-bw", "test74"));
        books.add(new Book("xxxxxxxxx-bx", "test75"));
        books.add(new Book("xxxxxxxxx-by", "test76"));
        books.add(new Book("xxxxxxxxx-bz", "test77"));
        books.add(new Book("xxxxxxxxx-ca", "test78"));
        books.add(new Book("xxxxxxxxx-cb", "test79"));
        books.add(new Book("xxxxxxxxx-cc", "test80"));
        books.add(new Book("xxxxxxxxx-cd", "test81"));
        books.add(new Book("xxxxxxxxx-ce", "test82"));
        books.add(new Book("xxxxxxxxx-cf", "test83"));
        books.add(new Book("xxxxxxxxx-cg", "test84"));
        books.add(new Book("xxxxxxxxx-ch", "test85"));
        books.add(new Book("xxxxxxxxx-ci", "test86"));
        books.add(new Book("xxxxxxxxx-cj", "test87"));
        books.add(new Book("xxxxxxxxx-ck", "test88"));
        books.add(new Book("xxxxxxxxx-cl", "test89"));
        books.add(new Book("xxxxxxxxx-cm", "test90"));
        books.add(new Book("xxxxxxxxx-cn", "test91"));
        books.add(new Book("xxxxxxxxx-co", "test92"));
        books.add(new Book("xxxxxxxxx-cp", "test93"));
        books.add(new Book("xxxxxxxxx-cq", "test94"));
        books.add(new Book("xxxxxxxxx-cr", "test95"));
        books.add(new Book("xxxxxxxxx-cs", "test96"));
        books.add(new Book("xxxxxxxxx-ct", "test97"));
        books.add(new Book("xxxxxxxxx-cu", "test98"));
        books.add(new Book("xxxxxxxxx-cv", "test99"));
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
        model.addAttribute("books", books.subList(0,5));
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
