package org.example.controllerssec02.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginatedResponse {
    private String message;
    private Object data;
    private int currentPage;

    public static ResponseEntity<PaginatedResponse> getResponse(HttpStatus status, String message, Object data, Integer currentPage) {
        return new ResponseEntity<>(
                new PaginatedResponse(message, data, currentPage),
                status
        );
    }
}
