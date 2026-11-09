//package dev.shaz.productservice.services;
//
//import dev.shaz.productservice.dtos.GenericProductDto;
//import dev.shaz.productservice.exceptions.NotFoundException;
//import dev.shaz.productservice.models.Category;
//import dev.shaz.productservice.models.Price;
//import dev.shaz.productservice.models.Product;
//import dev.shaz.productservice.repositories.CategoryRepository;
//import dev.shaz.productservice.repositories.PriceRepository;
//import dev.shaz.productservice.repositories.ProductRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class SelfProductServiceTest {
//
//    @Autowired
//    private SelfProductService selfProductService;
//
//    @MockBean
//    private ProductRepository productRepository;
//
//    @MockBean
//    private CategoryRepository categoryRepository;
//
//    @MockBean
//    private PriceRepository priceRepository;
//
//    //Test Cases for getProductById
//    @Test
//    public void testGetProductByIdReturnsCorrectResponse() throws NotFoundException {
//        Category category = new Category();
//        category.setName("Electronics");
//
//        Price price = new Price("Rupee", 100);
//
//        Product product = new Product();
//        product.setUuid(UUID.fromString("7586db38-5e35-4df8-b8c7-74becc2991bc"));
//        product.setTitle("Nokia");
//        product.setDescription("Old but Gold");
//        product.setImage("nokia_image_url");
//        product.setCategory(category);
//        product.setPrice(price);
//
//        Optional<Product> optionalProduct = Optional.of(product);
//
//        when(productRepository.findById(any())).thenReturn(optionalProduct);
//
//        Assertions.assertEquals("Nokia", selfProductService.getProductById("7586db38-5e35-4df8-b8c7-74becc2991bc").getTitle());
//    }
//
//    @Test
//    public void testGetProductByIdWhenRepoReturnsEmptyOptionalProduct() throws NotFoundException {
//        when(productRepository.findById(any())).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(NotFoundException.class,
//                () -> selfProductService.getProductById("7586db38-5e35-4df8-b8c7-74becc2991bc"));
//    }
//
//    //Test Cases for createProduct
//    @Test
//    public void testCreateProductIfWorkingAsExpected(){
//        GenericProductDto genericProductDto = new GenericProductDto();
//        genericProductDto.setId(1L);
//        genericProductDto.setTitle("Iphone");
//        genericProductDto.setDescription("Best phone ever");
//        genericProductDto.setCategory("Electronics");
//        genericProductDto.setImage("image_url");
//        genericProductDto.setPrice(100.0);
//        genericProductDto.setInventoryCount(1);
//
//        when(categoryRepository.save(any(Category.class))).thenReturn(new Category());
//        when(priceRepository.save(any(Price.class))).thenReturn(new Price());
//        when(productRepository.save(any(Product.class))).thenReturn(new Product());
//
//        GenericProductDto result = selfProductService.createProduct(genericProductDto);
//
//        verify(categoryRepository, times(1)).save(any(Category.class));
//        verify(priceRepository, times(1)).save(any(Price.class));
//        verify(productRepository, times(1)).save(any(Product.class));
//    }
//}
