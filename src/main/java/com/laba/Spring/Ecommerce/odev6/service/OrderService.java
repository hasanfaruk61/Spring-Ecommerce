package com.laba.Spring.Ecommerce.odev6.service;

import com.laba.Spring.Ecommerce.odev6.dto.request.CreateOrderRequestDto;
import com.laba.Spring.Ecommerce.odev6.dto.response.OrderResponseDto;
import com.laba.Spring.Ecommerce.odev6.entity.Order;
import com.laba.Spring.Ecommerce.odev6.entity.OrderProduct;
import com.laba.Spring.Ecommerce.odev6.entity.Product;
import com.laba.Spring.Ecommerce.odev6.repository.OrderProductRepository;
import com.laba.Spring.Ecommerce.odev6.repository.OrderRepository;
import com.laba.Spring.Ecommerce.odev6.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    public OrderService(ProductRepository productRepository,
                        OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Transactional
    public void createOrder(CreateOrderRequestDto requestDto) {
        long productId = requestDto.getProductId();
        Optional<Product> product = productRepository.findById(productId);
        String orderDescription = requestDto.getOrderDescription();

        Order order = new Order();
        order.setOrderNumber(requestDto.getOrderNumber());
        order.setOrderDate(requestDto.getOrderDate());
        order.setOrderDescription(requestDto.getOrderDescription());
        order.setTotalAmount(requestDto.getTotalAmount());
        orderRepository.save(order);

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product.get());
        orderProduct.setOrderProductDesc(orderDescription);

        orderProductRepository.save(orderProduct);
    }

    public OrderResponseDto getOneOrderByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        return toResponseDto(order);
    }

    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        return orders.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    private OrderResponseDto toResponseDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setOrderNumber(order.getOrderNumber());
        responseDto.setOrderDate(order.getOrderDate());
        responseDto.setOrderDescription(order.getOrderDescription());
        responseDto.setTotalAmount(order.getTotalAmount());
        responseDto.setCreateDate(order.getCreateDate());
        responseDto.setUpdateDate(order.getUpdateDate());
        return responseDto;
    }

    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        orderProductRepository.deleteByOrderId(orderId);
        orderRepository.delete(order);
    }
}
