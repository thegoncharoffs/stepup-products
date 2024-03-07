package ru.stepup.products.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepup.products.daos.ProductDao;
import ru.stepup.products.dtos.ProductDto;
import ru.stepup.products.entities.ProductEntity;
import ru.stepup.products.mappers.ProductMapper;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductDao productDao;

    public List<ProductEntity> getAll() {
        return productDao.getAll();
    }

    public Optional<ProductEntity> getById(Long id) {
        return productDao.getById(id);
    }

    public boolean update(ProductDto productDto) {
        return productDao.update(ProductMapper.dtoToEntity(productDto));
    }

    public boolean delete(Long id) {
        return productDao.delete(id);
    }

    public boolean create(ProductDto productDto) {
        return productDao.create(ProductMapper.dtoToEntity(productDto));
    }
}
