package com.shazahmed.searchservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document(indexName = "category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private UUID id;
    private String name;
    private List<Product> product;
}
