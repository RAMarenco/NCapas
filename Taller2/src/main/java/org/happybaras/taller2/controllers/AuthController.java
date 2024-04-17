package org.happybaras.taller2.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.happybaras.taller2.domain.dtos.LoginDTO;
import org.happybaras.taller2.domain.dtos.RegisterDTO;
import org.happybaras.taller2.domain.enums.LoginStatus;
import org.happybaras.taller2.domain.enums.RegisterStatus;
import org.happybaras.taller2.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    private AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    private ResponseEntity<?> Login(@RequestBody @Valid LoginDTO loginDTO, BindingResult errors) {
        try {
            if (errors.hasErrors()) {
                log.info("Hay errores");
                log.info(errors.toString());
                return new ResponseEntity<>(
                        "Bad Request",
                        HttpStatus.BAD_REQUEST
                );
            }

            LoginStatus status = authService.login(loginDTO);

            if (status == LoginStatus.NOT_VALID_IDENTIFIER) {
                return new ResponseEntity<>(
                        "Not a valid identifier",
                        HttpStatus.UNAUTHORIZED
                );
            }

            if (status == LoginStatus.NOT_FOUND) {
                return new ResponseEntity<>(
                        "User not found",
                        HttpStatus.NOT_FOUND
                );
            }

            if (status == LoginStatus.WRONG_PASSWORD) {
                return new ResponseEntity<>(
                        "Wrong Password",
                        HttpStatus.UNAUTHORIZED
                );
            }

            return new ResponseEntity<>(
                    HttpStatus.OK
            );

        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity<>(
                    "An unexpected error occurred",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/register")
    private ResponseEntity<?> Register(@RequestBody @Valid RegisterDTO registerDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            log.info(errors.toString());
            return new ResponseEntity<>(
                    "Bad Request",
                    HttpStatus.BAD_REQUEST
            );
        }

        RegisterStatus registerStatus = authService.register(registerDTO);

        log.info(registerStatus.toString());

        if (registerStatus == RegisterStatus.EMAIL_EXISTS) {
            return new ResponseEntity<>(
                    "Bad Request",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (registerStatus == RegisterStatus.USERNAME_EXISTS) {
            return new ResponseEntity<>(
                    "Bad Request",
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
