package com.laba.Spring.Ecommerce.odev7.service;

import com.laba.Spring.Ecommerce.odev7.dto.request.CreateProductRequestDto;
import com.laba.Spring.Ecommerce.odev7.dto.response.ProductResponseDto;
import com.laba.Spring.Ecommerce.odev7.entity.Product;
import com.laba.Spring.Ecommerce.odev7.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    }

    public Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new EntityNotFoundException("Product not found with id: " + productId);
        }
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return products.stream().map(this::toResponseDto).toList();
    }

    private ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setName(product.getName());
        responseDto.setCategory(product.getCategory());
        responseDto.setPhotoUrl(product.getPhotoUrl());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setCreateDate(product.getCreateDate());
        responseDto.setUpdateDate(product.getUpdateDate());
        return responseDto;
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        productRepository.delete(product);
    }

    public List<ProductResponseDto> getProductsByCategory(String category) {
        List<Product> products = productRepository.findAllByCategory(category);

        return products.stream().map(product -> {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setName(product.getName());
            productResponseDto.setCategory(product.getCategory());
            productResponseDto.setDescription(product.getDescription());
            productResponseDto.setPhotoUrl(product.getPhotoUrl());
            productResponseDto.setPrice(product.getPrice());
            productResponseDto.setCreateDate(product.getCreateDate());
            productResponseDto.setUpdateDate(product.getUpdateDate());
            return productResponseDto;

        }).toList();
    }

}
