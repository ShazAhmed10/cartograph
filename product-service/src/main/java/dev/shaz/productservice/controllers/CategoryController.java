package dev.shaz.productservice.controllers;

import dev.shaz.productservice.dtos.ProductListRequestDto;
import dev.shaz.productservice.exceptions.NotFoundException;
import dev.shaz.productservice.models.Product;
import dev.shaz.productservice.services.CategoryService;
import dev.shaz.productservice.services.SelfCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(@Qualifier("selfCategoryService") SelfCategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/singleCategory/{uuid}")
    public ResponseEntity<List<String>> getProductsFromCategoryId(@PathVariable("uuid") String uuid) throws NotFoundException {
        return new ResponseEntity<>(categoryService.getProductsFromCategoryId(uuid), HttpStatus.OK);
    }

    @GetMapping("/allCategories")
    public ResponseEntity<List<List<String>>> getProductsFromCategoryIds(@RequestBody ProductListRequestDto request){
       return new ResponseEntity<>(categoryService.getProductsFromCategoryIds(request.getUuids()), HttpStatus.OK);
    }
}
