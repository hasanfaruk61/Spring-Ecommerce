package com.laba.Spring.Ecommerce.odev11.controller;

import com.laba.Spring.Ecommerce.odev11.dto.request.CreateOrderRequestDto;
import com.laba.Spring.Ecommerce.odev11.dto.response.OrderResponseDto;
import com.laba.Spring.Ecommerce.odev11.exception.BusinessException;
import com.laba.Spring.Ecommerce.odev11.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
    private final OrderService orderService;
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequestDto requestDto) throws BusinessException, InterruptedException {
        orderService.createOrder(requestDto);
        return ResponseEntity.ok("Order created successfully!");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOneOrderByOrderId(@PathVariable Long orderId) {
        OrderResponseDto responseDto = orderService.getOneOrderByOrderId(orderId);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> responseDtos = orderService.getAllOrders();
        return ResponseEntity.ok(responseDtos);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }
}
