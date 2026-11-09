package com.shazahmed.searchservice.repositories;

import com.shazahmed.searchservice.models.Price;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends ElasticsearchRepository<Price, Long> {
    public Optional<Price> findByPrice(double amount);
}
