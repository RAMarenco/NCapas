package org.example.controllerssec02.services;

import org.example.controllerssec02.controllers.LibraryController;

import java.util.List;

public interface PaginationService<T> {
    int getTotalPages(List<T> data, int pageSize);
    List<T> pagination(List<T> data, int pageNumber, int pageSize);
}
