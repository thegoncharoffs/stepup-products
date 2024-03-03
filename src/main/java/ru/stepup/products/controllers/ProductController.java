package ru.stepup.products.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepup.products.entities.Product;
import ru.stepup.products.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class.getName());
    @Autowired
    ProductService productService;


    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAll() {
        List<Product> result = productService.getAll();

        if (result.isEmpty()) {
            logger.info("Products are empty");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        logger.info("Products " + result.toString());
        return new ResponseEntity<>(result, HttpStatus.OK);
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
    public void create(@RequestBody Product product) {
        productService.create(product);
        logger.info("Product " + product.toString() + " created");
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        productService.update(product);
        logger.info("Product " + product.toString() + " updated");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
        logger.info("Product with id" + id + " deleted");
    }
}
