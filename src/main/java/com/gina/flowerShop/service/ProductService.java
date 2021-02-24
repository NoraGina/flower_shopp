package com.gina.flowerShop.service;

import com.gina.flowerShop.web.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> findAll();
    Optional<ProductDto> findOne(Long idProduct);
    void save(ProductDto productDto);
    void delete(Long idProduct);
}
