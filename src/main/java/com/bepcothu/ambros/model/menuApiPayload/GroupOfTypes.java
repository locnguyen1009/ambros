package com.bepcothu.ambros.model.menuApiPayload;

import com.bepcothu.ambros.model.menu.ItemType;
import com.bepcothu.ambros.model.menu.MenuItem;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupOfTypes {
  ItemTypeReq typeReq;
  List<MenuItemReq> menuItemReqList;
}
