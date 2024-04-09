package org.example.controllerssec02.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagination {
    int totalPages;
    int pageSize;
}
