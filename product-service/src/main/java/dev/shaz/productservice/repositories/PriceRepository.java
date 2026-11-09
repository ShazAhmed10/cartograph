package dev.shaz.productservice.repositories;

import dev.shaz.productservice.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PriceRepository extends JpaRepository<Price, UUID> {
    @Query(value = "SELECT * FROM price as p WHERE p.price = :amount", nativeQuery = true)
    public Optional<Price> findByPrice(double amount);
}
