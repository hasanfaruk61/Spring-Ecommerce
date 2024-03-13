package com.laba.Spring.Ecommerce.odev6.repository;

import com.laba.Spring.Ecommerce.odev6.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
