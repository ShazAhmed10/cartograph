package com.shazahmed.searchservice.services;

import com.shazahmed.searchservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    public List<GenericProductDto> getAllProducts();
    public GenericProductDto createProduct(GenericProductDto genericProductDto);
}
