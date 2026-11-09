package dev.shaz.productservice.services;

import dev.shaz.productservice.dtos.GenericProductDto;
import dev.shaz.productservice.dtos.SortValue;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchService {
    public Page<GenericProductDto> searchProduct(String query,
                                                 int pageNumber,
                                                 int pageSize,
                                                 List<SortValue> sortValues);
}
