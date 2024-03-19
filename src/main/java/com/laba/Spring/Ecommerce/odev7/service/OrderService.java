package com.laba.Spring.Ecommerce.odev7.service;

import com.laba.Spring.Ecommerce.odev7.dto.request.CreateOrderRequestDto;
import com.laba.Spring.Ecommerce.odev7.dto.response.OrderResponseDto;
import com.laba.Spring.Ecommerce.odev7.entity.Order;
import com.laba.Spring.Ecommerce.odev7.entity.OrderProduct;
import com.laba.Spring.Ecommerce.odev7.entity.Product;
import com.laba.Spring.Ecommerce.odev7.entity.Users;
import com.laba.Spring.Ecommerce.odev7.repository.OrderProductRepository;
import com.laba.Spring.Ecommerce.odev7.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserService userService;
    private final MailService mailService;
    private final SmsService smsService;

    public OrderService(ProductService productService,
                        OrderRepository orderRepository,
                        OrderProductRepository orderProductRepository,
                        UserService userService, MailService mailService, SmsService smsService) {

        this.productService = productService;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.userService = userService;
        this.mailService = mailService;
        this.smsService = smsService;
    }

    @Transactional
    public void createOrder(CreateOrderRequestDto requestDto) throws InterruptedException {
        List<Long> productIds = requestDto.getProductIds();
        String orderDescription = requestDto.getOrderDescription();
        Long userId = requestDto.getUserId();

        double totalAmount = 0.0;

        for (Long productId : productIds) {
            Product product = productService.getProductById(productId);
            totalAmount += product.getPrice();
        }

        Order order = new Order();
        order.setOrderDate(new Date());
        order.setOrderDescription(requestDto.getOrderDescription());
        order.setTotalAmount(totalAmount);
        order.setCreateDate(new Date());
        order.setOrderNumber(UUID.randomUUID().toString());
        Optional<Users> userById = userService.findUserById(userId);
        Users users = userById.orElseThrow(() -> new EntityNotFoundException("User not found!"));

        orderRepository.save(order);

        productIds.stream()
                .map(productService::getProductById)
                .forEach(product -> {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setOrder(order);
                    orderProduct.setProduct(product);
                    orderProduct.setOrderProductDesc(orderDescription);
                    orderProductRepository.save(orderProduct);
                });
        //mailService.sendMailUser(order, users);
        smsService.sendSmsUser(order, users);

        System.out.println("mail send");
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