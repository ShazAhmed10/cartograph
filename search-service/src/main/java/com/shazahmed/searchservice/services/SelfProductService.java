package com.shazahmed.searchservice.services;

import com.shazahmed.searchservice.dtos.GenericProductDto;
import com.shazahmed.searchservice.models.Category;
import com.shazahmed.searchservice.models.Price;
import com.shazahmed.searchservice.models.Product;
import com.shazahmed.searchservice.repositories.CategoryRepository;
import com.shazahmed.searchservice.repositories.PriceRepository;
import com.shazahmed.searchservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private PriceRepository priceRepository;

    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              PriceRepository priceRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    public GenericProductDto convertProductToGenericProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setId(product.getId().toString());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setPrice(product.getPrice().getPrice());

        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();

        List<GenericProductDto> genericProductDtoList = new ArrayList<>();

        for(Product product : productList){
            genericProductDtoList.add(convertProductToGenericProductDto(product));
        }

        return genericProductDtoList;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Optional<Category> categoryOptional = categoryRepository.findByName(genericProductDto.getCategory());
        Category category;
        if(categoryOptional.isEmpty()){
            category = new Category();
            category.setName(genericProductDto.getCategory());
            categoryRepository.save(category);
        }
        else{
            category = categoryOptional.get();
        }

        Optional<Price> priceOptional = priceRepository.findByPrice(genericProductDto.getPrice());
        Price price;
        if(priceOptional.isEmpty()){
            price = new Price();
            price.setPrice(genericProductDto.getPrice());
            priceRepository.save(price);
        }
        else{
            price = priceOptional.get();
        }

        Optional<Product> productOptional = productRepository.findByTitle(genericProductDto.getTitle());
        Product product;
        if(productOptional.isEmpty()){
            product = new Product();

            product.setId(UUID.randomUUID());
            product.setTitle(genericProductDto.getTitle());
            product.setCategory(category);
            product.setPrice(price);

            return convertProductToGenericProductDto(productRepository.save(product));
        }
        else{
            return convertProductToGenericProductDto(productOptional.get());
        }
    }
}
