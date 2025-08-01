package com.bepcothu.ambros.model.menuApiPayload;

import com.bepcothu.ambros.model.menu.ItemType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MenuResp {
  List<ItemType> menu;
}
