package com.laba.Spring.Ecommerce.odev11.service;

import com.laba.Spring.Ecommerce.odev11.dto.request.CreateProductRequestDto;
import com.laba.Spring.Ecommerce.odev11.dto.response.ProductResponseDto;
import com.laba.Spring.Ecommerce.odev11.entity.Product;
import com.laba.Spring.Ecommerce.odev11.exception.BusinessException;
import com.laba.Spring.Ecommerce.odev11.exception.GeneralException;
import com.laba.Spring.Ecommerce.odev11.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        product.setCreateDate(new Date());
        product.setUpdateDate(new Date());

        saveProduct(product);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw new GeneralException("Product not found with id: " + productId);
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return products.stream().map(this::toResponseDto).toList();
    }

    private ProductResponseDto toResponseDto(Product product) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductResponseDto.class);
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
        if (currentStock <= 0) {
            throw new BusinessException("Insufficient stock for product with id: " + productId);
        }else {
            product.setStockQuantity(currentStock - 1);
        }
        productRepository.save(product);
    }
}
