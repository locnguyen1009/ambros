package com.bepcothu.ambros.controller;

import com.bepcothu.ambros.model.order.Order;
import com.bepcothu.ambros.model.orderApiPayload.OrderReq;
import com.bepcothu.ambros.model.orderApiPayload.OrderResp;
import com.bepcothu.ambros.service.order.OrderService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
  private OrderService orderService;

  @PostMapping("")
  public ResponseEntity<Order> createOrder(@RequestBody OrderReq orderReq){
    Order order = orderService.createOrder(orderReq);
    if(order == null){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No body enter");
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(order);
  }
  @GetMapping("/{orderId}")
  public OrderResp getOrderById(@PathVariable Long orderId){
    return orderService.getOrderById(orderId);
  }
}
