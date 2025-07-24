package com.bepcothu.ambros.service.menu;

import com.bepcothu.ambros.model.menuApiPayload.ItemTypeReq;
import com.bepcothu.ambros.model.menu.ItemType;
import java.util.List;

public interface ItemTypeServ {

  ItemType getItemTypeById(Long id);

  ItemType createItemType(ItemTypeReq itemTypeReq);

  List<ItemType> getAllType();
}
