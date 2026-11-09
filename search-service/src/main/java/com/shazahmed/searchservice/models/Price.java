package com.shazahmed.searchservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Getter
@Setter
@Document(indexName = "price")
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    private UUID id;
    private String currency;
    private double price;
}
