//package dev.shaz.productservice.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import dev.shaz.productservice.dtos.GenericProductDto;
//import dev.shaz.productservice.services.ProductService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ProductController.class)
//@AutoConfigureMockMvc
//public class ProductControllerTest {
//
//    @MockBean
//    private ProductService productService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    //Test Cases for getProductById
//    @Test
//    public void testGetProductByIdReturnsCorrectResponse() throws Exception {
//        GenericProductDto genericProductDto = new GenericProductDto();
//        genericProductDto.setId(1L);
//        genericProductDto.setTitle("Nokia");
//        genericProductDto.setDescription("Old but Gold");
//        genericProductDto.setImage("nokia_image_url");
//        genericProductDto.setCategory("Smart Phone");
//        genericProductDto.setPrice(30000.0);
//        genericProductDto.setInventoryCount(0);
//
//        when(productService.getProductById(any()))
//                .thenReturn(genericProductDto);
//
//        ResultActions resultActions = mockMvc.perform(get("/products/1"))
//                .andExpect(status().is(200))
//                .andExpect(content().json("{\"id\":1,\"title\":\"Nokia\",\"description\":\"Old but Gold\",\"image\":\"nokia_image_url\",\"category\":\"Smart Phone\",\"price\":30000.0,\"inventoryCount\":0}"))
//                .andExpect(jsonPath("$.id").value(1L));
//
//        String responseString = resultActions.andReturn().getResponse().getContentAsString();
//
//        Assertions.assertEquals("\"id\":1,\"title\":\"Nokia\",\"description\":\"Old but Gold\",\"image\":\"nokia_image_url \",\"category\":\"Smart Phone\",\"price\":30000.0,\"inventoryCount\":0",
//                responseString);
//
//        GenericProductDto responseDto = objectMapper.readValue(responseString, GenericProductDto.class);
//
//        Assertions.assertNotNull(responseDto);
//        Assertions.assertEquals(1L, responseDto.getId());
//    }
//}
