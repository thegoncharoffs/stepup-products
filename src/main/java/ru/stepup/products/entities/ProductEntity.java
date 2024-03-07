package ru.stepup.products.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.stepup.products.enums.ProductType;

@Data
@Builder
@AllArgsConstructor
public class ProductEntity {
    private Long id;

    private Long accountNumber;

    private int balance;

    private ProductType type;
}
