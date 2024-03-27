package com.laba.Spring.Ecommerce.unittest;

import com.laba.Spring.Ecommerce.odev7.dto.request.CreateProductRequestDto;
import com.laba.Spring.Ecommerce.odev7.dto.response.ProductResponseDto;
import com.laba.Spring.Ecommerce.odev7.entity.Product;
import com.laba.Spring.Ecommerce.odev7.exception.BusinessException;
import com.laba.Spring.Ecommerce.odev7.exception.GeneralException;
import com.laba.Spring.Ecommerce.odev7.repository.ProductRepository;
import com.laba.Spring.Ecommerce.odev7.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceUnitTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testSaveProduct() {

        CreateProductRequestDto requestDto = new CreateProductRequestDto();
        requestDto.setName("iphone14");
        requestDto.setCategory("phone");
        requestDto.setPhotoUrl("test_url");
        requestDto.setDescription("Test Description");
        requestDto.setPrice(50.0);
        requestDto.setStockQuantity(100);
        requestDto.setCreateDate(new Date());
        requestDto.setUpdateDate(new Date());

        productService.saveProduct(requestDto);

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testGetProductById() {

        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(product.getId(), result.getId());
    }

    @Test(expected = GeneralException.class)
    public void testGetProductById_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        productService.getProductById(1L);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        List<ProductResponseDto> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(products.size(), result.size());
    }

    @Test
    public void testDeleteProduct() {

        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).delete(any(Product.class));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteProduct_ProductNotFound() {

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        productService.deleteProduct(1L);
    }

    @Test
    public void testGetProductsByCategory() {

        String category = "phone";
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("iphone14");
        product1.setCategory("phone");
        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("iphone12");
        product2.setCategory("phone");
        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAllByCategory(category)).thenReturn(products);

        List<ProductResponseDto> result = productService.getProductsByCategory(category);

        assertNotNull(result);
    }

    @Test
    public void testReduceProductStock() throws BusinessException {

        Product product = new Product();
        product.setId(1L);
        product.setStockQuantity(10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.reduceProductStock(1L);

        assertEquals(9, product.getStockQuantity());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test(expected = BusinessException.class)
    public void testReduceProductStock_InsufficientStock() throws BusinessException {

        Product product = new Product();
        product.setId(1L);
        product.setStockQuantity(0);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.reduceProductStock(1L);
    }
}
