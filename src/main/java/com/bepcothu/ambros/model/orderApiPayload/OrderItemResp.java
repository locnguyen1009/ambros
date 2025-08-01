package com.bepcothu.ambros.model.orderApiPayload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResp {
  private String itemName;
  private int quantity;
  private double price;
}
