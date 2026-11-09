package dev.shaz.productservice.services;

import dev.shaz.productservice.thirdpartyclients.fakestore.FakeStoreProductClient;
import dev.shaz.productservice.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;
import dev.shaz.productservice.dtos.GenericProductDto;
import dev.shaz.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductClient fakeStoreProductClient;

    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient = fakeStoreProductClient;
    }

    public GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setId(fakeStoreProductDto.getId().toString());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setImage(fakeStoreProductDto.getImage());

        return genericProductDto;
    }

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductClient.getProductById(Long.parseLong(id)));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreProductClient.getAllProducts();

        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            GenericProductDto genericProductDto = convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);

            genericProductDtos.add(genericProductDto);
        }

        return genericProductDtos;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductClient.createProduct(product));
    }

    @Override
    public GenericProductDto deleteProduct(String id) {
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductClient.deleteProduct(Long.parseLong(id)));
    }

    @Override
    public GenericProductDto updateProductById(String id, GenericProductDto genericProductDto) throws NotFoundException {
        return null;
    }
}
