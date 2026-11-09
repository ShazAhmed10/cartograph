package com.shazahmed.searchservice.repositories;

import com.shazahmed.searchservice.models.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
    public Optional<Product> findByTitle(String title);

    @Override
    public List<Product> findAll();
}
