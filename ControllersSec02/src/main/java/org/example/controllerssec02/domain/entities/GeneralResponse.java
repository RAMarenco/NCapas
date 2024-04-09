package org.example.controllerssec02.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GeneralResponse <T> {
    private List<T> data;
}
