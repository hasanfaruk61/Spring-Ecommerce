package com.laba.Spring.Ecommerce.odev11.service;

import com.laba.Spring.Ecommerce.odev11.dto.request.CreateOrderRequestDto;
import com.laba.Spring.Ecommerce.odev11.dto.response.OrderResponseDto;
import com.laba.Spring.Ecommerce.odev11.entity.Order;
import com.laba.Spring.Ecommerce.odev11.entity.OrderProduct;
import com.laba.Spring.Ecommerce.odev11.entity.Product;
import com.laba.Spring.Ecommerce.odev11.entity.Users;
import com.laba.Spring.Ecommerce.odev11.exception.BusinessException;
import com.laba.Spring.Ecommerce.odev11.exception.GeneralException;
import com.laba.Spring.Ecommerce.odev11.repository.OrderProductRepository;
import com.laba.Spring.Ecommerce.odev11.repository.OrderRepository;
import com.laba.Spring.Ecommerce.odev11.service.smsService.OrderSmsService;
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
    private final OrderSmsService orderSmsService;

    public OrderService(ProductService productService,
                        OrderRepository orderRepository,
                        OrderProductRepository orderProductRepository,
                        UserService userService, OrderSmsService orderSmsService) {

        this.productService = productService;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.userService = userService;
        this.orderSmsService = orderSmsService;
    }

    public void createOrder(CreateOrderRequestDto requestDto) throws BusinessException {
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
        Users users = userById.orElseThrow(() -> new GeneralException("User not found!"));

        orderRepository.save(order);

        for (Long productId : productIds) {
            Product product = productService.getProductById(productId);
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProduct.setOrderProductDesc(orderDescription);
            orderProductRepository.save(orderProduct);
        }

        for (Long productId : productIds) {
            productService.reduceProductStock(productId);
        }

        orderSmsService.sendSmsUser(order, users);

        getCargoOffer(order.getId());
    }


    private void getCargoOffer(Long orderId) throws BusinessException {
        Optional<Order> orderById = orderRepository.findById(orderId);
        Order order = orderById.orElseThrow(() -> new GeneralException("Order not found with id: " + orderId));
        List<OrderProduct> orderProducts = orderProductRepository.findAllByOrder(order);
        Double totalPrice = 0.0;
        for (OrderProduct orderProduct : orderProducts) {
            totalPrice += orderProduct.getProduct().getPrice();
        }
        if (totalPrice < 50) {
            throw new BusinessException("No Offer for this Order!");
        }
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
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new GeneralException("Order not found with id: " + orderId));
        orderProductRepository.deleteByOrderId(orderId);
        orderRepository.delete(order);
    }
}
