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
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
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
                        Map.of("message", "Bad Request"),
                        HttpStatus.BAD_REQUEST
                );
            }

            LoginStatus status = authService.login(loginDTO);

            if (status == LoginStatus.NOT_VALID_IDENTIFIER) {
                return new ResponseEntity<>(
                        Map.of("message", "Not a valid identifier"),
                        HttpStatus.UNAUTHORIZED
                );
            }

            if (status == LoginStatus.NOT_FOUND) {
                return new ResponseEntity<>(
                        Map.of("message", "User not found"),
                        HttpStatus.NOT_FOUND
                );
            }

            if (status == LoginStatus.WRONG_PASSWORD) {
                return new ResponseEntity<>(
                        Map.of("message", "Wrong password"),
                        HttpStatus.UNAUTHORIZED
                );
            }

            return new ResponseEntity<>(
                    Map.of("message", "Login Successful"),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity<>(
                    Map.of("message", "An unexpected error occurred"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/register")
    private ResponseEntity<?> Register(@RequestBody @Valid RegisterDTO registerDTO, BindingResult errors) {
        try {
            if (errors.hasErrors()) {
                log.info(errors.toString());
                return new ResponseEntity<>(
                        Map.of("message", "Bad Request"),
                        HttpStatus.BAD_REQUEST
                );
            }

            RegisterStatus registerStatus = authService.register(registerDTO);

            log.info(registerStatus.toString());

            if (registerStatus == RegisterStatus.EMAIL_EXISTS) {
                return new ResponseEntity<>(
                        Map.of("message", "Email already exists"),
                        HttpStatus.BAD_REQUEST
                );
            }

            if (registerStatus == RegisterStatus.USERNAME_EXISTS) {
                return new ResponseEntity<>(
                        Map.of("message", "Username already exists"),
                        HttpStatus.BAD_REQUEST
                );
            }

            return new ResponseEntity<>(
                    Map.of("message", "Register Successful"),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity<>(
                    Map.of("message", "An unexpected error occurred"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
