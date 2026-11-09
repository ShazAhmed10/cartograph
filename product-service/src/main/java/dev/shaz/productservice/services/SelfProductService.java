package dev.shaz.productservice.services;

import dev.shaz.productservice.dtos.GenericProductDto;
import dev.shaz.productservice.dtos.UserServiceUserDto;
import dev.shaz.productservice.exceptions.NotFoundException;
import dev.shaz.productservice.models.Category;
import dev.shaz.productservice.models.Price;
import dev.shaz.productservice.models.Product;
import dev.shaz.productservice.repositories.CategoryRepository;
import dev.shaz.productservice.repositories.PriceRepository;
import dev.shaz.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private PriceRepository priceRepository;
    private RedisTemplate redisTemplate;
    private RestTemplate restTemplate;

    @Autowired
    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              PriceRepository priceRepository,
                              RedisTemplate redisTemplate,
                              RestTemplate restTemplate){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.redisTemplate = redisTemplate;
        this.restTemplate = restTemplate;
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
    public GenericProductDto getProductById(String id) throws NotFoundException {
        //Required logic for the method getProductById
        GenericProductDto cacheGenericProductDto
                = (GenericProductDto) redisTemplate.opsForValue().get(String.valueOf(id));
        if(cacheGenericProductDto != null){
            return cacheGenericProductDto;
        }

        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("The product required is not present");
        }
        Product product = optionalProduct.get();
        GenericProductDto genericProductDto = convertProductToGenericProductDto(product);

        redisTemplate.opsForValue().set(String.valueOf(id), genericProductDto);
        
        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAllProduct();

        List<GenericProductDto> genericProductDtoList = new ArrayList<>();

        for(Product product : productList){
            genericProductDtoList.add(convertProductToGenericProductDto(product));
        }

        return genericProductDtoList;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Optional<Category> categoryOptional = categoryRepository.findByName(genericProductDto.getCategory());
        Category category;
        if(categoryOptional.isEmpty()){
            category = new Category();
            category.setName(genericProductDto.getCategory());
            categoryRepository.save(category);
        }
        else{
            category = categoryOptional.get();
        }

        Optional<Price> priceOptional = priceRepository.findByPrice(genericProductDto.getPrice());
        Price price;
        if(priceOptional.isEmpty()){
            price = new Price();
            price.setPrice(genericProductDto.getPrice());
            priceRepository.save(price);
        }
        else{
            price = priceOptional.get();
        }

        Optional<Product> productOptional = productRepository.findByTitle(genericProductDto.getTitle());
        Product product;
        if(productOptional.isEmpty()){
            product = new Product();

            product.setUuid(UUID.randomUUID());
            product.setTitle(genericProductDto.getTitle());
            product.setDescription(genericProductDto.getDescription());
            product.setImage(genericProductDto.getImage());
            product.setCategory(category);
            product.setPrice(price);
            product.setInventoryCount(1);

            return convertProductToGenericProductDto(productRepository.save(product));
        }
        else{
            return convertProductToGenericProductDto(productOptional.get());
        }
    }

    @Override
    public GenericProductDto deleteProduct(String id) throws NotFoundException{
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("The product required for delete is not present");
        }
        Product product = optionalProduct.get();

        productRepository.deleteById(UUID.fromString(id));

        return convertProductToGenericProductDto(product);
    }

    @Override
    public GenericProductDto updateProductById(String id, GenericProductDto genericProductDto) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("The product required for update is not present");
        }
        Product product = optionalProduct.get();

        if(genericProductDto.getTitle() != null){
            product.setTitle(genericProductDto.getTitle());
        }

        if(genericProductDto.getDescription() != null){
            product.setDescription(genericProductDto.getDescription());
        }

        if(genericProductDto.getImage() != null){
            product.setImage(genericProductDto.getImage());
        }

        if(genericProductDto.getCategory() != null){
            Optional<Category> categoryOptional = categoryRepository.findByName(genericProductDto.getCategory());
            Category category;
            if(categoryOptional.isEmpty()){
                category = new Category();
                category.setName(genericProductDto.getCategory());
                Category savedCategory = categoryRepository.save(category);

                product.setCategory(savedCategory);
            }
            else{
                category = categoryOptional.get();
                product.setCategory(category);
            }
        }

        if(genericProductDto.getPrice() != null){
            Optional<Price> priceOptional = priceRepository.findByPrice(genericProductDto.getPrice());
            Price price;
            if(priceOptional.isEmpty()){
                price = new Price();
                price.setPrice(genericProductDto.getPrice());
                Price savedPrice = priceRepository.save(price);

                product.setPrice(savedPrice);
            }
            else{
                price = priceOptional.get();
                product.setPrice(price);
            }
        }

        if(genericProductDto.getInventoryCount() != product.getInventoryCount()){
            product.setInventoryCount(genericProductDto.getInventoryCount());
        }

        return convertProductToGenericProductDto(productRepository.save(product));
    }
}
