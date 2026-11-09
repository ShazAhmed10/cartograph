package dev.shaz.productservice.services;

import dev.shaz.productservice.dtos.GenericProductDto;
import dev.shaz.productservice.dtos.SortValue;
import dev.shaz.productservice.models.Product;
import dev.shaz.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("searchServiceImpl_1")
public class SearchServiceImpl_1 implements SearchService {
    private ProductRepository productRepository;

    @Autowired
    public SearchServiceImpl_1(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public GenericProductDto convertProductToGenericProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setId(product.getUuid().toString());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setInventoryCount(product.getInventoryCount());

        return genericProductDto;
    }

    @Override
    public Page<GenericProductDto> searchProduct(String query,
                                                 int pageNumber,
                                                 int pageSize,
                                                 List<SortValue> sortValues) {
        Sort sort = Sort.by("title");
        for(SortValue sortValue : sortValues){
            if(sortValue.getValue().equals("title")){
                if (sortValue.getOrder().equals("asc")) {
                    sort.ascending();
                }
                else{
                    sort.descending();
                }
            }

            if(sortValue.getValue().equals("price")){
                sort.by("price");
                if(sortValue.getOrder().equals("asc")){
                    sort.ascending();
                }
                else{
                    sort.descending();
                }
            }
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> productPage = productRepository.findAllByTitleContaining(query, pageable);

        List<Product> productList = productPage.get().toList();
        List<GenericProductDto> genericProductDtoList = new ArrayList<>();
        for(Product product : productList){
            genericProductDtoList.add(convertProductToGenericProductDto(product));
        }

        Page<GenericProductDto> genericProductDtoPage = new PageImpl<>
                (
                        genericProductDtoList,
                        productPage.getPageable(),
                        productPage.getTotalElements()
                );

        return genericProductDtoPage;
    }
}
