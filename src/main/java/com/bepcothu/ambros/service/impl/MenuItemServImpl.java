package com.bepcothu.ambros.service.impl;

import com.bepcothu.ambros.model.menu.MenuItem;
import com.bepcothu.ambros.model.menuApiPayload.MenuItemReq;
import com.bepcothu.ambros.model.menuApiPayload.StatusResp;
import com.bepcothu.ambros.repo.ItemTypeRepo;
import com.bepcothu.ambros.repo.MenuItemRepo;
import com.bepcothu.ambros.service.menu.MenuItemServ;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MenuItemServImpl implements MenuItemServ {
  private final MenuItemRepo menuItemRepo;
  private final ItemTypeRepo itemTypeRepo;

  public MenuItemServImpl(MenuItemRepo menuItemRepo, ItemTypeRepo itemTypeRepo) {
    this.menuItemRepo = menuItemRepo;
    this.itemTypeRepo = itemTypeRepo;
  }


  @Override
  public MenuItem createMenu(MenuItemReq menuItemReq) {
    MenuItem menuItem = new MenuItem();
    menuItem.setName(menuItemReq.getName());
    menuItem.setPrice(menuItemReq.getPrice());
    menuItem.setDescription(menuItemReq.getDescription());
//    menuItem.setItemType(menuItemReq.getItemType());
    return menuItemRepo.save(menuItem);
  }


  @Override
  public MenuItem getMenuItemById(Long menuId) {
    return menuItemRepo.findById(menuId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "item is not found for Id: " + menuId));
  }

  @Override
  public List<MenuItem> getAllMenuItem() {
    return menuItemRepo.findAll();
  }

  @Override
  public MenuItem updateItem(Long id, MenuItem menuItem) {
    if(this.getMenuItemById(id) == null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Item Id: " + id + "is not found.");
    }
    MenuItem item = this.getMenuItemById(id);
    if(menuItem.getName() != null){
      item.setName(menuItem.getName());
    }
    if(menuItem.getDescription() != null){
      item.setDescription(menuItem.getDescription());
    }
    if(menuItem.getPrice() != 0.0){
      item.setPrice(menuItem.getPrice());
    }

    return menuItemRepo.save(item);
  }

  @Override
  public StatusResp deleteItem(Long id) {
    if(menuItemRepo.findById(id).isEmpty()){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item is");
    }
    menuItemRepo.deleteById(id);
    String message = "Delete item Id: " + id + " successfully";
//    StatusResp resp = new StatusResp();
//    resp.setMessage(message);

    return new StatusResp(true, message);
  }
}
