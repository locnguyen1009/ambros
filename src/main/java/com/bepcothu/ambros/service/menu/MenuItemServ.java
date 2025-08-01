package com.bepcothu.ambros.service.menu;

import com.bepcothu.ambros.model.menu.MenuItem;
import com.bepcothu.ambros.model.menuApiPayload.MenuItemReq;
import com.bepcothu.ambros.model.menuApiPayload.MenuItemResp;
import com.bepcothu.ambros.model.menuApiPayload.StatusResp;
import java.util.List;

public interface MenuItemServ {
  public MenuItem createMenuItem(Long typeId, MenuItemReq menuItemReq);

  MenuItemResp getMenuItemById(Long menuId);

  List<MenuItemResp> getAllMenuItem();

  MenuItem updateItem(Long id, MenuItem menuItem);

  StatusResp deleteItem(Long id);
}
