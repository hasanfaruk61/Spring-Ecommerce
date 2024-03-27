package com.laba.Spring.Ecommerce.odev7.controller;

import com.laba.Spring.Ecommerce.odev7.dto.request.CreateProductRequestDto;
import com.laba.Spring.Ecommerce.odev7.dto.response.ProductResponseDto;
import com.laba.Spring.Ecommerce.odev7.entity.Product;
import com.laba.Spring.Ecommerce.odev7.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductRestController {
    private final ProductService productService;
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody CreateProductRequestDto requestDto) {
        productService.saveProduct(requestDto);
        return ResponseEntity.ok("Product created successfully");
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product productResponseDto = productService.getProductById(productId);
        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("productListByCategory/{category}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(@PathVariable String category) {
        List<ProductResponseDto> productList = productService.getProductsByCategory(category);
        return ResponseEntity.ok(productList);
    }
}
