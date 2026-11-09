package dev.shaz.productservice.repositories;

import dev.shaz.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query(value = "SELECT * FROM category as c WHERE c.name = :name", nativeQuery = true)
    public Optional<Category> findByName(String name);
}
