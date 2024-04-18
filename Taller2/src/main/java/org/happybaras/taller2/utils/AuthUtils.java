package org.happybaras.taller2.utils;

import org.happybaras.taller2.domain.enums.LoginStatus;
import org.happybaras.taller2.domain.enums.RegisterStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class AuthUtils {
    public static ResponseEntity<?> getLoginResponse(LoginStatus status) {
        return switch (status) {
            case NOT_VALID_IDENTIFIER -> new ResponseEntity<>(Map.of("message", "Not a valid identifier"), HttpStatus.UNAUTHORIZED);
            case NOT_FOUND -> new ResponseEntity<>(Map.of("message", "User not found"), HttpStatus.NOT_FOUND);
            case WRONG_PASSWORD -> new ResponseEntity<>(Map.of("message", "Wrong password"), HttpStatus.UNAUTHORIZED);
            case LOGIN_SUCCESSFUL -> new ResponseEntity<>(Map.of("message", "Login successful"), HttpStatus.OK);
        };
    }

    public static ResponseEntity<?> getRegisterResponse(RegisterStatus status) {
        return switch (status) {
            case EMAIL_EXISTS -> new ResponseEntity<>(Map.of("message", "Email already exists"), HttpStatus.BAD_REQUEST);
            case USERNAME_EXISTS -> new ResponseEntity<>(Map.of("message", "Username already exists"), HttpStatus.BAD_REQUEST);
            case REGISTER_SUCCESSFUL -> new ResponseEntity<>(Map.of("message", "Register successful"), HttpStatus.CREATED);
        };
    }
}
