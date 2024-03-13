package com.laba.Spring.Ecommerce.odev6.controller;

import com.laba.Spring.Ecommerce.odev6.dto.request.CreateProductRequestDto;
import com.laba.Spring.Ecommerce.odev6.dto.response.ProductResponseDto;
import com.laba.Spring.Ecommerce.odev6.service.ProductService;
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
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long productId) {
        ProductResponseDto productResponseDto = productService.getProductById(productId);
        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> productList = productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
