package com.laba.Spring.Ecommerce.odev7.utils;

import com.laba.Spring.Ecommerce.odev7.entity.Order;
import com.laba.Spring.Ecommerce.odev7.entity.OrderProduct;
import com.laba.Spring.Ecommerce.odev7.entity.Product;
import com.laba.Spring.Ecommerce.odev7.repository.OrderProductRepository;
import com.laba.Spring.Ecommerce.odev7.repository.OrderRepository;
import com.laba.Spring.Ecommerce.odev7.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UtilsOrderProduct {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    public UtilsOrderProduct(OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @PostConstruct
    public void test() {
        Product product = new Product();
        product.setName("iphone 12");
        product.setCategory("phone");
        product.setPhotoUrl("www.iphone12.com");
        product.setDescription("Best Iphone");
        product.setCreateDate(new Date());
        product.setUpdateDate(new Date());
        product.setPrice(32.000);
        productRepository.save(product);

        Product product1 = new Product();
        product1.setName("iphone 13");
        product1.setCategory("phone");
        product1.setPhotoUrl("www.iphone13.com");
        product1.setDescription("Best Iphone");
        product1.setCreateDate(new Date());
        product1.setUpdateDate(new Date());
        product1.setPrice(36.000);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("iphone 14");
        product2.setCategory("phone");
        product2.setPhotoUrl("www.iphone14.com");
        product2.setDescription("Best Iphone");
        product2.setCreateDate(new Date());
        product2.setUpdateDate(new Date());
        product2.setPrice(40.000);
        productRepository.save(product2);

        Order order = new Order();
        order.setOrderDescription("order desc1");
        orderRepository.save(order);

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProductRepository.save(orderProduct);
    }
}
