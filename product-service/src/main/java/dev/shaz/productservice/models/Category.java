package dev.shaz.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> product;
}
