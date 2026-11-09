package com.shazahmed.searchservice.controllers;

import com.shazahmed.searchservice.dtos.GenericProductDto;
import com.shazahmed.searchservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<GenericProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<GenericProductDto> createProduct(@RequestBody GenericProductDto genericProductDto){
        return new ResponseEntity<>(productService.createProduct(genericProductDto), HttpStatus.OK);
    }
}
