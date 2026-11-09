package com.shazahmed.searchservice.repositories;

import com.shazahmed.searchservice.models.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends ElasticsearchRepository<Category, Long> {
    public Optional<Category> findByName(String name);
}
