package com.bepcothu.ambros.service;

import com.bepcothu.ambros.model.menu.MenuItem;
import com.bepcothu.ambros.model.order.Order;
import com.bepcothu.ambros.model.order.OrderItem;
import com.bepcothu.ambros.model.order.OrderStatus;
import com.bepcothu.ambros.model.orderApiPayload.OrderItemResp;
import com.bepcothu.ambros.model.orderApiPayload.OrderReq;
import com.bepcothu.ambros.model.orderApiPayload.OrderResp;
import com.bepcothu.ambros.repo.menu.MenuItemRepo;
import com.bepcothu.ambros.repo.order.OrderItemRepo;
import com.bepcothu.ambros.repo.order.OrderRepo;
import com.bepcothu.ambros.service.order.OrderItemService;
import com.bepcothu.ambros.service.order.OrderService;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderServiceImpl implements OrderItemService, OrderService {
  private final MenuItemRepo menuItemRepo;
  private final OrderRepo orderRepo;
  private final OrderItemRepo orderItemRepo;

  public OrderServiceImpl(
      MenuItemRepo menuItemRepo, OrderRepo orderRepo, OrderItemRepo orderItemRepo) {
    this.menuItemRepo = menuItemRepo;
    this.orderRepo = orderRepo;
    this.orderItemRepo = orderItemRepo;
  }

  @Override
  public Order createOrder(OrderReq orderReq) {
    Order order = new Order();
    order.setCustomerName(orderReq.getCustomerName());
    order.setOrderTime(LocalDateTime.now());
    order.setStatus(OrderStatus.PENDING);

    List<OrderItem> items =
        orderReq.getMenuItemsIds().stream()
            .map(
                id -> {
                  MenuItem menuItem =
                      menuItemRepo
                          .findById(id)
                          .orElseThrow(
                              () ->
                                  new ResponseStatusException(
                                      HttpStatus.NOT_FOUND, "item id " + id + "is not found"));
                  return OrderItem.builder()
                      .itemName(menuItem.getName())
                      .menuItem(menuItem)
                      .price(menuItem.getPrice())
                      .quantity(1)
                      .order(order)
                      .build();
                })
            .toList();

    order.setItems(items);
    order.setTotalAmount(items.stream().mapToDouble(OrderItem::getPrice).sum());

    Order savedOrder = orderRepo.save(order);
    orderItemRepo.saveAll(items);
    return savedOrder;

  }

  @Override
  public OrderResp getOrderById(Long orderId) {
    Order currentOrder =
        orderRepo
            .findById(orderId)
            .orElseThrow(
                () ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "order Id: " + orderId + "is not found"));
    List<OrderItem> items = currentOrder.getItems();
    Set<OrderItemResp> itemResp = new HashSet<>();
    for (OrderItem item : items) {
      OrderItemResp resp =
          new OrderItemResp(item.getItemName(), item.getQuantity(), item.getPrice());
      if (itemResp.contains(resp)) {
        resp.setQuantity(resp.getQuantity() + 1);
      } else itemResp.add(resp);
    }

    return new OrderResp(
        currentOrder.getCustomerName(),
        currentOrder.getOrderTime(),
        currentOrder.getStatus(),
        itemResp,
        currentOrder.getTotalAmount());
  }
}
