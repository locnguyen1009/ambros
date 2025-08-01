package com.bepcothu.ambros.model.order;

import com.bepcothu.ambros.model.menu.MenuItem;
import com.bepcothu.ambros.model.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String itemName;
  private int quantity;
  private double price;

  @ManyToOne
  @JoinColumn(name = "orderId")
  @JsonIgnore
  private Order order;

  @ManyToOne
  @JoinColumn(name = "itemId")
  @JsonIgnore
  private MenuItem menuItem;



}
