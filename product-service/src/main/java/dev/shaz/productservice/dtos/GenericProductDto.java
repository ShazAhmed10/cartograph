package dev.shaz.productservice.dtos;

import dev.shaz.productservice.models.Category;
import dev.shaz.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class GenericProductDto implements Serializable {
    private String id;
    private String title;
    private String description;
    private String image;
    private String category;
    private Double price;
    private int inventoryCount;
}
