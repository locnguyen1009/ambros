package com.bepcothu.ambros.model.menuApiPayload;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MenuCreation {
//  private List<GroupOfTypes> menu;
  private List<ItemTypeReq> menu;

}
