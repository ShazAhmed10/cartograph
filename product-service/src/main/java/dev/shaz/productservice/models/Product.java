package dev.shaz.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Category category;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Price price;

    private int inventoryCount;
}
