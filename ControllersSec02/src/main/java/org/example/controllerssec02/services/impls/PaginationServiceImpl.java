package org.example.controllerssec02.services.impls;

import org.example.controllerssec02.services.PaginationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaginationServiceImpl<T> implements PaginationService<T> {
    public int getTotalPages(List<T> data, int pageSize) {
        int totalBooks = data.size();
        return (int) Math.ceil((double) totalBooks / pageSize);
    }
    public List<T> pagination(List<T> data, int pageNumber, int pageSize) {
        int totalPages = getTotalPages(data, pageSize);

        if (pageNumber > totalPages) {
            throw new IllegalArgumentException("Page number exceeds total number of pages.");
        }

        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, data.size());

        return data.subList(startIndex, endIndex);
    }
}
