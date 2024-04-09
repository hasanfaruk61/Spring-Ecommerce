package com.laba.Spring.Ecommerce.odev11.utils;

import com.laba.Spring.Ecommerce.odev11.entity.Product;
import com.laba.Spring.Ecommerce.odev11.exception.BusinessException;
import com.laba.Spring.Ecommerce.odev11.repository.OrderProductRepository;
import com.laba.Spring.Ecommerce.odev11.repository.OrderRepository;
import com.laba.Spring.Ecommerce.odev11.repository.ProductRepository;
import com.laba.Spring.Ecommerce.odev11.service.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UtilsOrderProduct {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductService productService;
    public UtilsOrderProduct(OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository, ProductService productService ) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
        this.productService = productService;
    }

    @PostConstruct
    public void test() throws BusinessException {
        Product product = new Product();
        product.setName("iphone 11");
        product.setCategory("phone");
        product.setPhotoUrl("www.iphone12.com");
        product.setDescription("Best Iphone");
        product.setStockQuantity(10);
        product.setCreateDate(new Date());
        product.setUpdateDate(new Date());
        product.setPrice(10.000);
        productRepository.save(product);

        Product product1 = new Product();
        product1.setName("iphone 12");
        product1.setCategory("phone");
        product1.setPhotoUrl("www.iphone13.com");
        product1.setDescription("Best Iphone");
        product1.setStockQuantity(10);
        product1.setCreateDate(new Date());
        product1.setUpdateDate(new Date());
        product1.setPrice(45.000);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("iphone 13");
        product2.setCategory("phone");
        product2.setPhotoUrl("www.iphone14.com");
        product2.setDescription("Best Iphone");
        product2.setStockQuantity(10);
        product2.setCreateDate(new Date());
        product2.setUpdateDate(new Date());
        product2.setPrice(55.000);
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("iphone 14");
        product3.setCategory("phone");
        product3.setPhotoUrl("www.iphone14.com");
        product3.setDescription("Best Iphone");
        product3.setStockQuantity(10);
        product3.setCreateDate(new Date());
        product3.setUpdateDate(new Date());
        product3.setPrice(55.000);
        productRepository.save(product3);

        Product product4 = new Product();
        product4.setName("iphone 15");
        product4.setCategory("phone");
        product4.setPhotoUrl("www.iphone14.com");
        product4.setDescription("Best Iphone");
        product4.setStockQuantity(10);
        product4.setCreateDate(new Date());
        product4.setUpdateDate(new Date());
        product4.setPrice(55.000);
        productRepository.save(product4);

        Product product5 = new Product();
        product5.setName("iphone 16");
        product5.setCategory("phone");
        product5.setPhotoUrl("www.iphone14.com");
        product5.setDescription("Best Iphone");
        product5.setStockQuantity(10);
        product5.setCreateDate(new Date());
        product5.setUpdateDate(new Date());
        product5.setPrice(55.000);
        productRepository.save(product5);

       /* Order order = new Order();
        order.setOrderDescription("order desc1");
        orderRepository.save(order);
        productService.reduceProductStock(product.getId());
        productService.reduceProductStock(product1.getId());
        productService.reduceProductStock(product2.getId());

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setProduct(product1);
        orderProduct.setProduct(product2);
        orderProductRepository.save(orderProduct);*/
    }
}
