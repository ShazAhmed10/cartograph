package com.shazahmed.searchservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Getter
@Setter
@Document(indexName = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private UUID id;
    private String title;
    private Category category;
    private Price price;
}
