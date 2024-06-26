package com.laba.Spring.Ecommerce.odev11.repository;

import com.laba.Spring.Ecommerce.odev11.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByCategory(String category);
}
