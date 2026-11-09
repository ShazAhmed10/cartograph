package com.shazahmed.searchservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private String id;
    private String title;
    private String category;
    private Double price;
}
