package ru.stepup.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.stepup.products.enums.ProductType;

@Getter
@AllArgsConstructor
public class ProductDto {
    private Long id;

    private Long accountNumber;

    private int balance;

    private ProductType type;
}
