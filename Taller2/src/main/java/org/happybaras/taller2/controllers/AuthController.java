package org.happybaras.taller2.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.happybaras.taller2.domain.dtos.LoginDTO;
import org.happybaras.taller2.domain.dtos.RegisterDTO;
import org.happybaras.taller2.domain.enums.LoginStatus;
import org.happybaras.taller2.domain.enums.RegisterStatus;
import org.happybaras.taller2.services.AuthService;
import org.happybaras.taller2.utils.AuthUtils;
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
        if (errors.hasErrors()) {
            return new ResponseEntity<>(
                    Map.of("message", "Bad Request"),
                    HttpStatus.BAD_REQUEST
            );
        }

        LoginStatus status = authService.login(loginDTO);
        return AuthUtils.getLoginResponse(status);
    }

    @PostMapping("/register")
    private ResponseEntity<?> Register(@RequestBody @Valid RegisterDTO registerDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(
                    Map.of("message", "Bad Request"),
                    HttpStatus.BAD_REQUEST
            );
        }

        RegisterStatus registerStatus = authService.register(registerDTO);
        return AuthUtils.getRegisterResponse(registerStatus);
    }
}
