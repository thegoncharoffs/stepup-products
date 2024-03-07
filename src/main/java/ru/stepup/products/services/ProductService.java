package ru.stepup.products.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepup.products.daos.ProductDao;
import ru.stepup.products.dtos.ProductDto;
import ru.stepup.products.entities.Product;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductDao productDao;

    public List<Product> getAll() {
        return productDao.getAll();
    }

    public Optional<Product> getById(Long id) {
        return productDao.getById(id);
    }

    public boolean update(ProductDto productDto) {
        return productDao.update(toProduct(productDto));
    }

    public boolean delete(Long id) {
        return productDao.delete(id);
    }

    public boolean create(ProductDto productDto) {
        return productDao.create(toProduct(productDto));
    }

    private static Product toProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .accountNumber(productDto.getAccountNumber())
                .balance(productDto.getBalance())
                .type(productDto.getType())
                .build();
    }
}
