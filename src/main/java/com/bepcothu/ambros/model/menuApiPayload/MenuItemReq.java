package com.bepcothu.ambros.model.menuApiPayload;

import com.bepcothu.ambros.model.menu.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuItemReq {
  private Long id;
  private String name;
  private double price;
  private String description;


}
