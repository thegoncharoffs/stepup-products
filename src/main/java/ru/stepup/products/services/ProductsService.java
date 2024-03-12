package ru.stepup.products.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepup.products.dtos.ProductDto;
import ru.stepup.products.mappers.ProductMapper;
import ru.stepup.products.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    public List<ProductDto> findAll() {
        return productsRepository.findAll().stream().map(ProductMapper::entityToDto).collect(Collectors.toList());
    }

    public Optional<ProductDto> findById(Long id) {
        return productsRepository.findById(id).map(ProductMapper::entityToDto);
    }

    public void update(ProductDto productDto) {
        productsRepository.save(ProductMapper.dtoToEntity(productDto));
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public void create(ProductDto productDto) {
        productsRepository.save(ProductMapper.dtoToEntity(productDto));
    }
}
