package ru.stepup.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stepup.products.daos.ProductDao;
import ru.stepup.products.entities.Product;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {
    @Autowired
    ProductDao productDao;

    public List<Product> getAll() {
        return productDao.getAll();
    }

    public Optional<Product> getById(Long id) {
        return productDao.getEntityById(id);
    }

    public boolean update(Product user) {
        return productDao.update(user);
    }

    public boolean delete(Long id) {
        return productDao.delete(id);
    }

    public boolean create(Product user) {
        return productDao.create(user);
    }
}
