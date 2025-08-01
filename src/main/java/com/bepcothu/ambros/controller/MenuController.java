package com.bepcothu.ambros.controller;

import com.bepcothu.ambros.model.menu.ItemType;
import com.bepcothu.ambros.model.menu.MenuItem;
import com.bepcothu.ambros.model.menuApiPayload.ItemTypeReq;
import com.bepcothu.ambros.model.menuApiPayload.ItemTypeResp;
import com.bepcothu.ambros.model.menuApiPayload.ItemTypeUpdate;
import com.bepcothu.ambros.model.menuApiPayload.MenuCreation;
import com.bepcothu.ambros.model.menuApiPayload.MenuItemReq;
import com.bepcothu.ambros.model.menuApiPayload.MenuItemResp;
import com.bepcothu.ambros.model.menuApiPayload.MenuResp;
import com.bepcothu.ambros.model.menuApiPayload.StatusResp;
import com.bepcothu.ambros.service.menu.ItemTypeServ;
import com.bepcothu.ambros.service.menu.MenuItemServ;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
  private final ItemTypeServ itemTypeServ;
  private final MenuItemServ menuItemServ;

  @PostMapping("/item")
  public ResponseEntity<MenuItem> createMenuItem(
      @RequestParam Long typeId, @RequestBody MenuItemReq menuItemReq) {
    MenuItem item = menuItemServ.createMenuItem(typeId, menuItemReq);
    if (item == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "something went wrong");
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(item);
  }

  @GetMapping("/item/{menuId}")
  public ResponseEntity<MenuItemResp> getMenuItemById(@PathVariable Long menuId) {
    MenuItemResp itemResp =  menuItemServ.getMenuItemById(menuId);
    return ResponseEntity.ok(itemResp);
  }

  @GetMapping("/item")
  public List<MenuItemResp> getAllMenuItem() {
    return menuItemServ.getAllMenuItem();
  }

  @PutMapping("/item/{id}")
  public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
    return menuItemServ.updateItem(id, menuItem);
  }

  @DeleteMapping("/item/{id}")
  public ResponseEntity<StatusResp> deleteMenuItem(@PathVariable Long id) {
    StatusResp resp = menuItemServ.deleteItem(id);
    return ResponseEntity.ok(resp);
  }


//  Item Type endpoint

  @PostMapping("/item_type")
  public ResponseEntity<ItemType> createItemType(@RequestBody ItemTypeReq itemTypeReq) {
    ItemType itemType = itemTypeServ.createItemType(itemTypeReq);
    if (itemType == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
    } else return ResponseEntity.status(HttpStatus.CREATED).body(itemType);
  }

  @GetMapping("/item_type/{id}")
  public ResponseEntity<ItemTypeResp> getItemTypeById(@PathVariable Long id) {
    ItemTypeResp itemTypeResp = itemTypeServ.getItemTypeById(id);
    return ResponseEntity.ok().body(itemTypeResp);
  }
  @PutMapping("/item_type/{typeId}")
  public ResponseEntity<ItemType> updateItemTypeById(@PathVariable Long typeId, @RequestBody ItemTypeUpdate typeUpdate){
    ItemType type = itemTypeServ.updateType(typeId, typeUpdate);
    if(type == null){
      throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Body is require");
    }
    return ResponseEntity.ok(type);
  }

  @DeleteMapping("/item_type/{id}")
  public ResponseEntity<StatusResp> deleteType(@PathVariable Long id) {
    StatusResp resp = itemTypeServ.deleteType(id);
    return ResponseEntity.ok(resp);
  }
  @GetMapping("")
  public ResponseEntity<MenuResp> getMenu() {
    return ResponseEntity.ok(itemTypeServ.getMenu());
  }

  @PostMapping("")
  public MenuResp createMenu(@RequestBody MenuCreation menuCreation){
    return itemTypeServ.createMenu(menuCreation);
  }

}
