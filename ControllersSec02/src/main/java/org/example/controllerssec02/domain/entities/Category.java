package org.example.controllerssec02.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sec02_categories")
public class Category {
    @Id
    @Column(name = "category_id")
    private String code;

    private String name;
}