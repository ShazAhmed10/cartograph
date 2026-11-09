package dev.shaz.productservice.controllers;

import dev.shaz.productservice.dtos.ExceptionDto;
import dev.shaz.productservice.dtos.GenericProductDto;
import dev.shaz.productservice.exceptions.NotFoundException;
import dev.shaz.productservice.security.JwtData;
import dev.shaz.productservice.security.TokenValidator;
import dev.shaz.productservice.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private TokenValidator tokenValidator;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService,
                             TokenValidator tokenValidator){
        this.productService = productService;
        this.tokenValidator = tokenValidator;
    }

    @GetMapping("/singleProduct/{id}")
    public ResponseEntity<GenericProductDto> getProductById(
            @Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @Nullable @RequestParam Long userId,
            @PathVariable("id") String id) throws NotFoundException {

        //JwtData jwtData = tokenValidator.validateToken(authToken, userId);

        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<GenericProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<GenericProductDto> createProduct(@RequestBody GenericProductDto genericProductDto){
        return new ResponseEntity<>(productService.createProduct(genericProductDto), HttpStatus.OK);
    }

    @DeleteMapping("/removeProduct/{id}")
    public ResponseEntity<GenericProductDto> deleteProduct(@PathVariable("id") String id) throws NotFoundException{
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<GenericProductDto> updateProductById(@PathVariable("id") String id, @RequestBody GenericProductDto genericProductDto) throws NotFoundException{
        return new ResponseEntity<>(productService.updateProductById(id, genericProductDto), HttpStatus.OK);
    }

//    The below exception Handler is specific to only this controller
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
//        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()), HttpStatus.NOT_FOUND);
//    }
}
