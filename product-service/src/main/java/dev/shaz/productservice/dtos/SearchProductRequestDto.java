package dev.shaz.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchProductRequestDto {
    private String query;
    private int pageNumber;
    private int pageSize;
    private List<SortValue> sortValues;
}
