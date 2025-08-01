package com.bepcothu.ambros.model.orderApiPayload;

import com.bepcothu.ambros.model.order.OrderItem;
import com.bepcothu.ambros.model.order.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResp {

  private String customerName;
  private LocalDateTime orderTime;
  private OrderStatus status;
  private Set<OrderItemResp> itemResp;
  private double totalAmount;
}
