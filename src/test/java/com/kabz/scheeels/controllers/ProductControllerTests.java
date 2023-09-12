package com.kabz.scheeels.controllers;

import com.kabz.scheeels.entities.Product;
import com.kabz.scheeels.infrastructure.ProductNotFoundException;
import com.kabz.scheeels.models.ProductListModel;
import com.kabz.scheeels.services.ProductService;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setName("water bottle");
        product.setActive(true);
    }

    @Test
    public void getProducts_shouldReturnAllItems() throws Exception {
        when(productService.getAllActiveProducts()).thenReturn(List.of(product));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void getProduct_withIdReturnsProduct() throws Exception {
        when(productService.getById(anyLong())).thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(product.getName()));
    }

    @Test
    public void getProduct_withNonExistingIdReturnsProperError() throws Exception {
        when(productService.getById(anyLong())).thenThrow(new ProductNotFoundException(222L));

        mockMvc.perform(get("/products/222"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message", containsString("222")));

    }

    @Test
    public void getPaginatedProducts_categoryShouldBeNull() throws Exception {
        Page<Product> pages = new PageImpl<>(List.of(product), Pageable.ofSize(1), 1);

        when(productService.getProducts(any()))
                .thenReturn(new ProductListModel(null, pages));

        mockMvc.perform(get("/products/page/11"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentCategory").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.products.content", hasSize(1)));

        verify(productService, atLeastOnce()).getProducts(any());
    }
}
