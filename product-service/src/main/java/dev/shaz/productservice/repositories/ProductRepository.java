package dev.shaz.productservice.repositories;

import dev.shaz.productservice.dtos.GenericProductDto;
import dev.shaz.productservice.models.Price;
import dev.shaz.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
//    *** Three EXAMPLES ON HOW TO WRITE REPOSITORY METHODS ***

//    Product findByTitleAndPrice_currency(String title, String currency);

//    @Query(value = "select * from product where title = :title", nativeQuery = true)
//    List<Product> findByTitleSQL(String title);

//    @Query(value = "select Product from Product where Product.title = :title", nativeQuery = false)
//    List<Product> findByTitleHQL(String title);

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    public List<Product> findAllProduct();

    @Query(value = "SELECT * FROM product as p WHERE p.title = :title", nativeQuery = true)
    public Optional<Product> findByTitle(String title);

    public Page<Product> findAllByTitleContaining(String query, Pageable pageable);
}
