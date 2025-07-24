package com.bepcothu.ambros.model.menuApiPayload;

import com.bepcothu.ambros.model.menu.MenuItem;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ItemTypeReq {
  private String category;

  private List<MenuItem> menuItemList;

}
