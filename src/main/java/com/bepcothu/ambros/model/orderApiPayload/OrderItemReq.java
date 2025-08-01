package com.bepcothu.ambros.model.orderApiPayload;

import com.bepcothu.ambros.model.menu.MenuItem;
import com.bepcothu.ambros.model.order.Order;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemReq {
  private Order order;

  private MenuItem menuItem;

  private int quantity;
  private double price;
}
