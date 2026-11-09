package dev.shaz.productservice.services;

import dev.shaz.productservice.dtos.GenericProductDto;
import dev.shaz.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {

    public GenericProductDto getProductById(String Id) throws NotFoundException;
    public List<GenericProductDto> getAllProducts();
    public GenericProductDto createProduct(GenericProductDto genericProductDto);
    public GenericProductDto deleteProduct(String id) throws NotFoundException;
    public GenericProductDto updateProductById(String id, GenericProductDto genericProductDto) throws NotFoundException;
}
