package com.bepcothu.ambros.model.menuApiPayload;

import com.bepcothu.ambros.model.menu.MenuItem;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemTypeResp {
  private Long typeId;
  private String category;
  private List<MenuItem> menuItem = new ArrayList<>();
}
