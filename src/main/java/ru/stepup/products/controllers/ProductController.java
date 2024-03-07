package ru.stepup.products.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepup.products.dtos.ProductDto;
import ru.stepup.products.entities.Product;
import ru.stepup.products.services.ProductService;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class.getName());

    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        List<Product> result = productService.getAll();
        logger.info("Products " + result.toString());
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Optional<Product> product = productService.getById(id);

        if (product.isEmpty()) {
            logger.info("Product whith id=" + id + " doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductDto productDto) {
        productService.create(productDto);
        logger.info("Product " + productDto + " created");
    }

    @PutMapping("/{id}")
    public void update(@RequestBody ProductDto productDto) {
        productService.update(productDto);
        logger.info("Product " + productDto + " updated");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
        logger.info("Product with id" + id + " deleted");
    }
}
