package ru.stepup.products.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepup.products.dtos.ProductDto;
import ru.stepup.products.entities.ProductEntity;
import ru.stepup.products.services.ProductService;

import java.util.List;
import java.util.Optional;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @GetMapping
    public List<ProductEntity> getAll() {
        List<ProductEntity> result = productService.getAll();
        log.info("Products " + result.toString());
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Optional<ProductEntity> product = productService.getById(id);

        if (product.isEmpty()) {
            log.info("Product whith id=" + id + " doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductDto productDto) {
        productService.create(productDto);
        log.info("Product " + productDto + " created");
    }

    @PutMapping("/{id}")
    public void update(@RequestBody ProductDto productDto) {
        productService.update(productDto);
        log.info("Product " + productDto + " updated");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
        log.info("Product with id" + id + " deleted");
    }
}
