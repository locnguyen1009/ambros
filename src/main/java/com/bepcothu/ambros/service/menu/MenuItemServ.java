package com.bepcothu.ambros.service.menu;

import com.bepcothu.ambros.model.menu.MenuItem;
import com.bepcothu.ambros.model.menuApiPayload.MenuItemReq;
import com.bepcothu.ambros.model.menuApiPayload.StatusResp;
import java.util.List;

public interface MenuItemServ {
  public MenuItem createMenu(MenuItemReq menuItemReq);

  MenuItem getMenuItemById(Long menuId);

  List<MenuItem> getAllMenuItem();

  MenuItem updateItem(Long id, MenuItem menuItem);

  StatusResp deleteItem(Long id);
}
