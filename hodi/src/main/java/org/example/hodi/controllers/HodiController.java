package org.example.hodi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hodi")
public class HodiController {
    @GetMapping("/")
    public ResponseEntity<String> getHodi() {

        return new ResponseEntity<>(
                "Hodi",
                HttpStatus.OK
        );
    }

    @GetMapping("/{name}")
    public ResponseEntity<String> getHodiName(@PathVariable String name) {
        return new ResponseEntity<>(
                "Hodi, " + name,
                HttpStatus.OK
        );
    }
}
