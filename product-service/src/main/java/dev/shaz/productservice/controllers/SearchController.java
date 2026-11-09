package dev.shaz.productservice.controllers;

import dev.shaz.productservice.dtos.GenericProductDto;
import dev.shaz.productservice.dtos.SearchProductRequestDto;
import dev.shaz.productservice.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(@Qualifier("searchServiceImpl_1") SearchService searchService){
        this.searchService = searchService;
    }

    @PostMapping("/product")
    public ResponseEntity<Page<GenericProductDto>> searchProduct(
            @RequestBody SearchProductRequestDto requestDto){
        Page<GenericProductDto> response = searchService.searchProduct(
                requestDto.getQuery(),
                requestDto.getPageNumber(),
                requestDto.getPageSize(),
                requestDto.getSortValues()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
