package ru.stepup.products.mappers;

import ru.stepup.products.dtos.ProductDto;
import ru.stepup.products.entities.ProductEntity;

public class ProductMapper {
    public static ProductEntity dtoToEntity(ProductDto productDto) {
        return ProductEntity.builder()
                .id(productDto.getId())
                .accountNumber(productDto.getAccountNumber())
                .balance(productDto.getBalance())
                .type(productDto.getType())
                .build();
    }
}
