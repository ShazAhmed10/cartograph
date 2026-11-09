package dev.shaz.productservice.services;

import dev.shaz.productservice.exceptions.NotFoundException;
import dev.shaz.productservice.models.Product;

import java.util.List;

public interface CategoryService {
    public List<String> getProductsFromCategoryId(String id) throws NotFoundException;
    public List<List<String>> getProductsFromCategoryIds(List<String> uuids);
}
