package dev.shaz.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseModel{

    @ManyToMany
    @JoinTable(
            name = "products_orders",
            joinColumns = @JoinColumn(name = "orderID"),
            inverseJoinColumns = @JoinColumn(name = "productID")
    )
    private List<Product> products;
}
