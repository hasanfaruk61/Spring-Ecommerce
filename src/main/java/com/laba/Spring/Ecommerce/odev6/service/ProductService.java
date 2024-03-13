package com.laba.Spring.Ecommerce.odev6.service;

import com.laba.Spring.Ecommerce.odev6.dto.request.CreateProductRequestDto;
import com.laba.Spring.Ecommerce.odev6.dto.response.ProductResponseDto;
import com.laba.Spring.Ecommerce.odev6.entity.Product;
import com.laba.Spring.Ecommerce.odev6.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void saveProduct(CreateProductRequestDto requestDto) {
        Product product = new Product();
        product.setName(requestDto.getName());
        product.setCategory(requestDto.getCategory());
        product.setPhotoUrl(requestDto.getPhotoUrl());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());
        product.setCreateDate(requestDto.getCreateDate());
        product.setUpdateDate(requestDto.getUpdateDate());

        productRepository.save(product);
        ResponseEntity.ok().build();
    }

    public ProductResponseDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).get();
        return toResponseDto(product);
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();

        return products.stream().map(this::toResponseDto).toList();
    }

    private ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setName(product.getName());
        responseDto.setCategory(product.getCategory());
        responseDto.setPhotoUrl(product.getPhotoUrl());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        return responseDto;
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        productRepository.delete(product);
    }
}
