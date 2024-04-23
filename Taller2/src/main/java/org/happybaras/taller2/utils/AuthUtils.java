package org.happybaras.taller2.utils;

import org.happybaras.taller2.domain.dtos.GeneralResponse;
import org.happybaras.taller2.domain.enums.LoginStatus;
import org.happybaras.taller2.domain.enums.RegisterStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class AuthUtils {
    public static ResponseEntity<?> getLoginResponse(LoginStatus status) {
        return switch (status) {
            case NOT_VALID_IDENTIFIER -> GeneralResponse.builder().status(HttpStatus.UNAUTHORIZED).message("Not a valid identifier").getResponse();
            case NOT_FOUND -> GeneralResponse.builder().message("User not found").status(HttpStatus.NOT_FOUND).getResponse();
            case WRONG_PASSWORD -> GeneralResponse.builder().message("Wrong password").status(HttpStatus.UNAUTHORIZED).getResponse();
            case LOGIN_SUCCESSFUL -> GeneralResponse.builder().message("Login succesful").getResponse();
        };
    }

    public static ResponseEntity<?> getRegisterResponse(RegisterStatus status) {
        return switch (status) {
            case EMAIL_EXISTS -> GeneralResponse.builder().message("Email already exists").status(HttpStatus.BAD_REQUEST).getResponse();
            case USERNAME_EXISTS -> GeneralResponse.builder().message("Username already exists").status(HttpStatus.BAD_REQUEST).getResponse();
            case REGISTER_SUCCESSFUL -> GeneralResponse.builder().message("Register succesful").status(HttpStatus.CREATED).getResponse();
        };
    }
}
