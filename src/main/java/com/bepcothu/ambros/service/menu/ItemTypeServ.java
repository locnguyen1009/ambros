package com.bepcothu.ambros.service.menu;

import com.bepcothu.ambros.model.menuApiPayload.ItemTypeReq;
import com.bepcothu.ambros.model.menu.ItemType;
import com.bepcothu.ambros.model.menuApiPayload.ItemTypeResp;
import com.bepcothu.ambros.model.menuApiPayload.ItemTypeUpdate;
import com.bepcothu.ambros.model.menuApiPayload.MenuCreation;
import com.bepcothu.ambros.model.menuApiPayload.MenuResp;
import com.bepcothu.ambros.model.menuApiPayload.StatusResp;
import java.awt.Menu;

public interface ItemTypeServ {

  ItemTypeResp getItemTypeById(Long id);

  ItemType createItemType(ItemTypeReq itemTypeReq);
  ItemType updateType(Long typeId, ItemTypeUpdate typeUpdate);

  MenuResp getMenu();

  MenuResp createMenu(MenuCreation menuCreation);

  StatusResp deleteType(Long id);
}
