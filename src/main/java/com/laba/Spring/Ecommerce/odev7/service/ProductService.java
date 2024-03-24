package com.laba.Spring.Ecommerce.odev7.service;

import com.laba.Spring.Ecommerce.odev7.dto.request.CreateProductRequestDto;
import com.laba.Spring.Ecommerce.odev7.dto.response.ProductResponseDto;
import com.laba.Spring.Ecommerce.odev7.entity.Product;
import com.laba.Spring.Ecommerce.odev7.exception.BusinessException;
import com.laba.Spring.Ecommerce.odev7.exception.GeneralException;
import com.laba.Spring.Ecommerce.odev7.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        product.setStockQuantity(requestDto.getStockQuantity());
        product.setCreateDate(requestDto.getCreateDate());
        product.setUpdateDate(requestDto.getUpdateDate());

        saveProduct(product);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new GeneralException("Product not found with id: " + productId);
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
        responseDto.setStockQuantity(product.getStockQuantity());
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
            productResponseDto.setStockQuantity(product.getStockQuantity());
            productResponseDto.setCreateDate(product.getCreateDate());
            productResponseDto.setUpdateDate(product.getUpdateDate());
            return productResponseDto;

        }).toList();
    }

    @Transactional
    public void reduceProductStock(Long productId) throws BusinessException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));

        int currentStock = product.getStockQuantity();
        if (currentStock > 0) {
            product.setStockQuantity(currentStock - 1);
            productRepository.save(product);
        } else {
            throw new BusinessException("Insufficient stock for product with id: " + productId);
        }
    }


}
