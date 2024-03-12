package ru.stepup.products.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.stepup.products.dtos.ProductDto;
import ru.stepup.products.exceptions.ResourceNotFoundException;
import ru.stepup.products.services.ProductsService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final ProductsService productsService;

    @GetMapping
    public List<ProductDto> getAll() {
        List<ProductDto> result = productsService.findAll();
        log.info("Products all " + result.toString());
        return productsService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        log.info("Product find by id " + id);
        return productsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductDto productDto) {
        productsService.create(productDto);
        log.info("Product " + productDto + " created");
    }

    @PutMapping
    public void update(@RequestBody ProductDto productDto) {
        productsService.update(productDto);
        log.info("Product " + productDto + " updated");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productsService.deleteById(id);
        log.info("Product with id" + id + " deleted");
    }
}
